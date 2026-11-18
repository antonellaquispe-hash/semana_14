# Laboratorio S14 – Config Server y Resiliencia

Proyecto Maven multimódulo para Java 17, Spring Boot 3.5.3 y Spring Cloud 2025.0.0.

## Módulos
- `config-server`: servidor de configuración centralizada (puerto 8888).
- `eureka-server`: registro de servicios (puerto 8761).
- `gateway-server`: puerta de entrada (puerto 8080).
- `categoria-service`: CRUD de categorías (8082).
- `producto-service`: CRUD de productos y Circuit Breaker al consultar categorías (8083).
- `cliente-service`: CRUD de clientes y Circuit Breaker al consultar pedidos (8084).
- `pedido-service`: pedidos; Retry al consultar clientes y Bulkhead al consultar productos (8085).

## Requisitos
- JDK 17.
- Maven 3.9 o superior.
- IntelliJ IDEA.
- Docker Desktop (para MySQL).

## 1. Base de datos
Desde la carpeta raíz:
```bash
docker compose up -d
```
MySQL queda disponible en `localhost:3306`, usuario `root`, contraseña `root123`. Se crean las cuatro bases de datos DEV y sus equivalentes PROD.

## 2. Abrir el proyecto
En IntelliJ IDEA, seleccionar **Open** y elegir la carpeta raíz `Laboratorio_S14_ConfigServer_Resiliencia`. Esperar a que Maven importe los siete módulos.

## 3. Orden de ejecución
Ejecutar cada clase `main` en una ventana independiente, en este orden:
1. `ConfigServerApplication`
2. `EurekaServerApplication`
3. `GatewayServerApplication`
4. `CategoriaServiceApplication`
5. `ProductoServiceApplication`
6. `ClienteServiceApplication`
7. `PedidoServiceApplication`

Los microservicios importan su configuración desde Config Server y usan el perfil `dev` por defecto.

## 4. URLs de comprobación
- Config Server categoría: http://localhost:8888/categoria-service/dev
- Config Server producto: http://localhost:8888/producto-service/dev
- Config Server cliente: http://localhost:8888/cliente-service/dev
- Config Server pedido: http://localhost:8888/pedido-service/dev
- Eureka: http://localhost:8761
- Actuator producto: http://localhost:8083/actuator/health

## 5. Endpoints
Directos o mediante Gateway (puerto 8080):
- `GET /api/categorias`
- `GET /api/productos`
- `GET /api/clientes`
- `GET /api/pedidos`
- `GET /api/clientes/pedidos/consulta` (prueba cliente → pedido)
- `GET /api/pruebas/demora` en cliente (5 s) y producto/pedido (10 s)

Ejemplos:
```bash
curl http://localhost:8080/api/categorias
curl http://localhost:8080/api/productos
curl http://localhost:8080/api/clientes
curl http://localhost:8080/api/pedidos
```

## 6. Pruebas de resiliencia del laboratorio
### Circuit Breaker producto → categoría
1. Consultar `http://localhost:8083/api/productos` con categoría activa.
2. Detener `CategoriaServiceApplication`.
3. Volver a consultar productos. Debe aparecer `Categoría temporal no disponible`.
4. Reiniciar categoría y esperar la ventana de recuperación configurada.

### Circuit Breaker cliente → pedido
1. Consultar `http://localhost:8084/api/clientes/pedidos/consulta`.
2. Detener `PedidoServiceApplication`.
3. Repetir la consulta: el fallback devuelve una lista vacía.

### Retry pedido → cliente
1. Consultar `http://localhost:8085/api/pedidos`.
2. Detener `ClienteServiceApplication`.
3. Revisar la consola de pedido: se realizan hasta 3 intentos, con 2 segundos entre intentos, y luego se ejecuta el fallback.

### Bulkhead pedido → producto
1. Consultar `http://localhost:8085/api/pedidos` con los servicios activos.
2. Abrir varias solicitudes concurrentes al mismo endpoint.
3. El Bulkhead limita a 2 las llamadas concurrentes al producto; las solicitudes que excedan el límite reciben el fallback.

### Demoras solicitadas
- Cliente: `GET http://localhost:8084/api/pruebas/demora` (5 segundos).
- Producto: `GET http://localhost:8083/api/pruebas/demora` (10 segundos).
Estas rutas sirven para evidenciar la demora de los servicios. Para observar el Bulkhead sobre la llamada Feign de pedidos, se debe generar concurrencia hacia `GET /api/pedidos` mientras el producto responde lentamente.

## 7. Perfiles DEV y PROD
Los archivos están en `config-repo/`:
- `*-service.yml`: configuración común.
- `*-service-dev.yml`: puertos 8082–8085 y bases locales.
- `*-service-prod.yml`: puertos 9092–9095 y variables `DB_URL`, `DB_USER`, `DB_PASSWORD`.

Para cambiar a PROD, ejecutar el microservicio con la variable de entorno `SPRING_PROFILES_ACTIVE=prod`. No activar PROD sin configurar primero la base de datos productiva.

## 8. Configuración Git remota (opcional)
El laboratorio muestra un repositorio GitHub como backend de Config Server. Este proyecto usa inicialmente el backend `native` con la carpeta `config-repo` para que pueda ejecutarse sin depender de una cuenta GitHub. Si el docente exige Git remoto, crear un repositorio `config-repo`, subir el contenido de esa carpeta y cambiar en `config-server/src/main/resources/application.yml`:
```yaml
spring:
  profiles:
    active: git
  cloud:
    config:
      server:
        git:
          uri: https://github.com/TU_USUARIO/config-repo
```
Retirar `native.search-locations` al hacer ese cambio.

## Nota académica
El ZIP incluye una implementación funcional de referencia y datos iniciales para facilitar las pruebas. Las evidencias fotográficas/capturas deben obtenerse ejecutando el proyecto en tu equipo. Revisa los endpoints y los resultados con el docente, especialmente las pruebas de concurrencia del Bulkhead.
