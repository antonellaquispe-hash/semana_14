package com.tecsup.model;
import jakarta.persistence.*;
@Entity @Table(name="pedido")
public class Pedido {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 private Long clienteId; private Long productoId; private Integer cantidad; private Double total; private String estado;
 @Transient private Object cliente; @Transient private Object producto;
 public Pedido(){} public Pedido(Long clienteId,Long productoId,Integer cantidad,Double total,String estado){this.clienteId=clienteId;this.productoId=productoId;this.cantidad=cantidad;this.total=total;this.estado=estado;}
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public Long getClienteId(){return clienteId;} public void setClienteId(Long v){this.clienteId=v;}
 public Long getProductoId(){return productoId;} public void setProductoId(Long v){this.productoId=v;}
 public Integer getCantidad(){return cantidad;} public void setCantidad(Integer v){this.cantidad=v;}
 public Double getTotal(){return total;} public void setTotal(Double v){this.total=v;}
 public String getEstado(){return estado;} public void setEstado(String v){this.estado=v;}
 public Object getCliente(){return cliente;} public void setCliente(Object v){this.cliente=v;}
 public Object getProducto(){return producto;} public void setProducto(Object v){this.producto=v;}
}
