package com.tecsup.services;
import com.tecsup.client.ClienteClient;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.stereotype.Service;
@Service
public class ClienteRetryService {
 private final ClienteClient client;
 public ClienteRetryService(ClienteClient client){this.client=client;}
 @Retry(name="clienteRetry",fallbackMethod="clienteFallback")
 public Object obtenerCliente(Long id){System.out.println("Consultando cliente (Retry)...");return client.obtenerCliente(id);}
 public Object clienteFallback(Long id,Throwable ex){System.out.println("Fallback Retry cliente: "+ex.getMessage());return java.util.Map.of("id",id,"nombre","Cliente temporal no disponible");}
}
