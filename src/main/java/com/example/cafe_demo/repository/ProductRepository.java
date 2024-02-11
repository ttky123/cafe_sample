package com.example.cafe_demo.repository;

import com.example.cafe_demo.entity.Product;
import com.example.cafe_demo.repository.queryDsl.ProductRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryCustom {
    Page<Product> findByOwnerPhoneNumber(String phoneNumber, Pageable pageable);
    List<Product> findByOwnerPhoneNumberAndNameContainingOrInitialSoundContaining(String phoneNumber, String name, String initial);
}