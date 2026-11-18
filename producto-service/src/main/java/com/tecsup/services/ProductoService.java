package com.tecsup.services;
import com.tecsup.dto.CategoriaDTO;
import com.tecsup.model.Producto;
import com.tecsup.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class ProductoService {
 private final ProductoRepository repository; private final CategoriaCircuitService categoriaService;
 public ProductoService(ProductoRepository repository,CategoriaCircuitService categoriaService){this.repository=repository;this.categoriaService=categoriaService;}
 public List<Producto> listar(){List<Producto> lista=repository.findAll();lista.forEach(this::completarCategoria);return lista;}
 public Producto obtener(Long id){return repository.findById(id).map(p->{completarCategoria(p);return p;}).orElse(null);}
 public Producto guardar(Producto p){Producto guardado=repository.save(p);completarCategoria(guardado);return guardado;}
 public Producto actualizar(Long id,Producto entrada){return repository.findById(id).map(p->{p.setNombre(entrada.getNombre());p.setPrecio(entrada.getPrecio());p.setStock(entrada.getStock());p.setCategoriaId(entrada.getCategoriaId());Producto saved=repository.save(p);completarCategoria(saved);return saved;}).orElse(null);}
 public void eliminar(Long id){repository.deleteById(id);}
 private void completarCategoria(Producto p){if(p.getCategoriaId()!=null){CategoriaDTO dto=categoriaService.obtenerCategoria(p.getCategoriaId());p.setCategoria(dto);}}
}
