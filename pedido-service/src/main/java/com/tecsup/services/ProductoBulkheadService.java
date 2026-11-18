package com.tecsup.services;
import com.tecsup.client.ProductoClient;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import org.springframework.stereotype.Service;
@Service
public class ProductoBulkheadService {
 private final ProductoClient client;
 public ProductoBulkheadService(ProductoClient client){this.client=client;}
 @Bulkhead(name="productoBulkhead",fallbackMethod="productoFallback")
 public Object obtenerProducto(Long id){System.out.println("Consultando producto (Bulkhead)...");return client.obtenerProducto(id);}
 public Object productoFallback(Long id,Throwable ex){System.out.println("Fallback Bulkhead producto: "+ex.getMessage());return java.util.Map.of("id",id,"nombre","Producto temporal no disponible");}
}
