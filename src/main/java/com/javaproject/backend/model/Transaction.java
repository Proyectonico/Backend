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
    private Double amount;

    @NotNull(message = "the transaction must have a date")
    private LocalDate date;

    @NotBlank(message = "this field can not be empty")
    private String name;

    
    private String account;

    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id=id;
    }
    public Double getAmount(){
        return amount;
    }
    public void setAmount(Double amount){
        this.amount=amount;
    }
    public LocalDate getDate(){
        return date;
    }
    public void setDate(LocalDate date){
        this.date=date;
    }
    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getAccount(){
        return account;
    }
    public void setAccount(String account){
        this.account=account;
    }
}
