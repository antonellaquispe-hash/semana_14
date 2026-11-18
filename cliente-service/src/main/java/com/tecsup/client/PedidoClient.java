package com.tecsup.client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;
@FeignClient(name="pedido-service")
public interface PedidoClient { @GetMapping("/api/pedidos") List<Object> listarPedidos(); }
