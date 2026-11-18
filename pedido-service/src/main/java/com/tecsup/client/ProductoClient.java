package com.tecsup.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@FeignClient(name="producto-service")
public interface ProductoClient { @GetMapping("/api/productos/{id}") Object obtenerProducto(@PathVariable("id") Long id); }
