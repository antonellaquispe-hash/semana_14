package com.tecsup.controller;
import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import com.tecsup.services.PedidoCircuitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController @RequestMapping("/api/clientes")
public class ClienteController {
 private final ClienteRepository repository; private final PedidoCircuitService pedidoService;
 public ClienteController(ClienteRepository repository,PedidoCircuitService pedidoService){this.repository=repository;this.pedidoService=pedidoService;}
 @GetMapping public List<Cliente> listar(){return repository.findAll();}
 @GetMapping("/{id}") public ResponseEntity<Cliente> obtener(@PathVariable Long id){return repository.findById(id).map(ResponseEntity::ok).orElseGet(()->ResponseEntity.notFound().build());}
 @PostMapping public Cliente crear(@RequestBody Cliente c){return repository.save(c);}
 @GetMapping("/pedidos/consulta") public List<Object> pedidos(){return pedidoService.listarPedidos();}
}
