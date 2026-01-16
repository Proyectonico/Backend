package com.javaproject.backend.model;
import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
@Entity
public class Transaction {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "the transaction must have an ammount")
    @Positive(message = "the transaction must be greater than 0")
    private Double monto;

    @NotNull(message = "the transaction must have a date")
    private LocalDate fecha;

    @NotBlank(message = "this field can not be empty")
    private String nombre;

    
    private String descripcion;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    public Double getMonto(){
        return monto;
    }
    public void setMonto(Double monto){
        this.monto=monto;
    }
    public LocalDate getFecha(){
        return fecha;
    }
    public void setFecha(LocalDate fecha){
        this.fecha=fecha;
    }
    public String getNombre(){
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public String getDescripcion(){
        return descripcion;
    }
    public void setDescripcion(String descripcion){
        this.descripcion=descripcion;
    }
}
