package com.tecsup.config;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner cargarDatos(ProductoRepository repository) { return args -> { if(repository.count()==0){repository.save(new Producto("Laptop",2500.0,10,1L));repository.save(new Producto("Mouse",50.0,30,1L));} }; }
}
