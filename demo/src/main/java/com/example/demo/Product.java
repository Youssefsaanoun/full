package com.example.demo;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@JsonInclude

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    
    private  Long id ; 
    private String nom ; 
    private double price ;

}
