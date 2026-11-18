package com.tecsup.controller;
import com.tecsup.model.Categoria;
import com.tecsup.repository.CategoriaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {
 private final CategoriaRepository repository;
 public CategoriaController(CategoriaRepository repository){this.repository=repository;}
 @GetMapping public List<Categoria> listar(){return repository.findAll();}
 @GetMapping("/{id}") public ResponseEntity<Categoria> obtener(@PathVariable Long id){return repository.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping public Categoria crear(@RequestBody Categoria categoria){return repository.save(categoria);}
 @PutMapping("/{id}") public ResponseEntity<Categoria> actualizar(@PathVariable Long id,@RequestBody Categoria entrada){return repository.findById(id).map(c->{c.setNombre(entrada.getNombre());c.setEstado(entrada.getEstado());return ResponseEntity.ok(repository.save(c));}).orElseGet(()->ResponseEntity.notFound().build());}
 @DeleteMapping("/{id}") public ResponseEntity<Void> eliminar(@PathVariable Long id){if(!repository.existsById(id))return ResponseEntity.notFound().build();repository.deleteById(id);return ResponseEntity.noContent().build();}
}
