package com.example.cafe_demo.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String category;
    private Double price;
    private Double cost;
    private String name;
    private String description;
    private String barcode;
    private Date expirationDate;
    private String size; // "small" or "large"
    private String initialSound; // 초성 필드 추가
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "phone_number")
    private Owner owner;
}