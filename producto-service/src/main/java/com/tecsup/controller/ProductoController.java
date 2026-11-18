package com.tecsup.controller;
import com.tecsup.model.Producto;
import com.tecsup.services.ProductoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/productos")
public class ProductoController {
 private final ProductoService service;
 public ProductoController(ProductoService service){this.service=service;}
 @GetMapping public List<Producto> listar(){return service.listar();}
 @GetMapping("/{id}") public ResponseEntity<Producto> obtener(@PathVariable Long id){Producto p=service.obtener(id);return p==null?ResponseEntity.notFound().build():ResponseEntity.ok(p);}
 @PostMapping public Producto crear(@RequestBody Producto p){return service.guardar(p);}
 @PutMapping("/{id}") public ResponseEntity<Producto> actualizar(@PathVariable Long id,@RequestBody Producto p){Producto r=service.actualizar(id,p);return r==null?ResponseEntity.notFound().build():ResponseEntity.ok(r);}
 @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id){service.eliminar(id);return ResponseEntity.noContent().build();}
}
