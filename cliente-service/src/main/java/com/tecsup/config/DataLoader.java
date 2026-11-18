package com.tecsup.config;
import com.tecsup.model.Cliente;
import com.tecsup.repository.ClienteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner cargarDatos(ClienteRepository repository) { return args -> { if(repository.count()==0){repository.save(new Cliente("Ana Torres","ana@example.com"));repository.save(new Cliente("Luis Pérez","luis@example.com"));} }; }
}
