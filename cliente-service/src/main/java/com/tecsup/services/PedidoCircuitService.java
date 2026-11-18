package com.tecsup.services;
import com.tecsup.client.PedidoClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class PedidoCircuitService {
 private final PedidoClient client;
 public PedidoCircuitService(PedidoClient client){this.client=client;}
 @CircuitBreaker(name="pedidoDesdeCliente",fallbackMethod="pedidosFallback")
 public List<Object> listarPedidos(){return client.listarPedidos();}
 public List<Object> pedidosFallback(Throwable ex){System.out.println("Fallback cliente-pedido: "+ex.getMessage());return List.of();}
}
