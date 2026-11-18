package com.tecsup.services;
import com.tecsup.client.CategoriaClient;
import com.tecsup.dto.CategoriaDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
@Service
public class CategoriaCircuitService {
 private final CategoriaClient client;
 public CategoriaCircuitService(CategoriaClient client){this.client=client;}
 @CircuitBreaker(name="categoriaService",fallbackMethod="categoriaFallback")
 public CategoriaDTO obtenerCategoria(Long id){System.out.println("Llamando a categoria-service...");return client.obtenerCategoria(id);}
 public CategoriaDTO categoriaFallback(Long id,Throwable ex){System.out.println("Circuit Breaker activado: "+ex.getMessage());CategoriaDTO dto=new CategoriaDTO();dto.setId(id);dto.setNombre("Categoría temporal no disponible");return dto;}
}
