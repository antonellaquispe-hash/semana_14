package com.tecsup.services;
import com.tecsup.model.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PedidoService {
 private final PedidoRepository repository; private final ClienteRetryService clienteService; private final ProductoBulkheadService productoService;
 public PedidoService(PedidoRepository repository,ClienteRetryService clienteService,ProductoBulkheadService productoService){this.repository=repository;this.clienteService=clienteService;this.productoService=productoService;}
 public List<Pedido> listar(){List<Pedido> pedidos=repository.findAll();pedidos.forEach(this::completar);return pedidos;}
 public Pedido obtener(Long id){return repository.findById(id).map(p->{completar(p);return p;}).orElse(null);}
 public Pedido guardar(Pedido p){Pedido saved=repository.save(p);completar(saved);return saved;}
 private void completar(Pedido p){if(p.getClienteId()!=null)p.setCliente(clienteService.obtenerCliente(p.getClienteId()));if(p.getProductoId()!=null)p.setProducto(productoService.obtenerProducto(p.getProductoId()));}
}
