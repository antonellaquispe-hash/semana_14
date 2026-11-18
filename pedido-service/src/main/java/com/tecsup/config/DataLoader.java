package com.tecsup.config;
import com.tecsup.model.Pedido;
import com.tecsup.repository.PedidoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner cargarDatos(PedidoRepository repository) { return args -> { if(repository.count()==0){repository.save(new Pedido(1L,1L,1,2500.0,"REGISTRADO"));repository.save(new Pedido(2L,2L,2,100.0,"REGISTRADO"));} }; }
}
