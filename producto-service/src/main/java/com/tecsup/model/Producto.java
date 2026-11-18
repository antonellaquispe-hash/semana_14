package com.tecsup.model;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonInclude;
@Entity @Table(name="producto")
public class Producto {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private String nombre; private Double precio; private Integer stock; private Long categoriaId;
 @Transient @JsonInclude(JsonInclude.Include.NON_NULL) private Object categoria;
 public Producto() {}
 public Producto(String nombre,Double precio,Integer stock,Long categoriaId){this.nombre=nombre;this.precio=precio;this.stock=stock;this.categoriaId=categoriaId;}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getNombre(){return nombre;} public void setNombre(String nombre){this.nombre=nombre;}
 public Double getPrecio(){return precio;} public void setPrecio(Double precio){this.precio=precio;}
 public Integer getStock(){return stock;} public void setStock(Integer stock){this.stock=stock;}
 public Long getCategoriaId(){return categoriaId;} public void setCategoriaId(Long categoriaId){this.categoriaId=categoriaId;}
 public Object getCategoria(){return categoria;} public void setCategoria(Object categoria){this.categoria=categoria;}
}
