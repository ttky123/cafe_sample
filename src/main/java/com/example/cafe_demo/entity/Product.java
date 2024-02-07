package com.example.cafe_demo.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;
    private Double price;
    private Double cost;
    private String name;
    private String description;
    private String barcode;
    private Date expirationDate;
    private String size; // "small" or "large"

    @ManyToOne
    @JoinColumn(name = "created_by")
    private Owner owner; // 상품을 생성한 사장님
}