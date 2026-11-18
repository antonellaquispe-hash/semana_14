package com.tecsup.config;
import com.tecsup.model.Categoria;
import com.tecsup.repository.CategoriaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class DataLoader {
 @Bean CommandLineRunner cargarDatos(CategoriaRepository repository) { return args -> { if(repository.count()==0){repository.save(new Categoria("Tecnología","ACTIVO"));repository.save(new Categoria("Hogar","ACTIVO"));} }; }
}
