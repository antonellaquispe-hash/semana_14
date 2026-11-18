package com.tecsup.controller;
import com.tecsup.model.Pedido;
import com.tecsup.services.PedidoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/pedidos")
public class PedidoController {
 private final PedidoService service;
 public PedidoController(PedidoService service){this.service=service;}
 @GetMapping public List<Pedido> listar(){return service.listar();}
 @GetMapping("/{id}") public ResponseEntity<Pedido> obtener(@PathVariable Long id){Pedido p=service.obtener(id);return p==null?ResponseEntity.notFound().build():ResponseEntity.ok(p);}
 @PostMapping public Pedido crear(@RequestBody Pedido p){return service.guardar(p);}
}
