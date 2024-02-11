package com.example.cafe_demo.repository.queryDsl;
// ProductRepositoryCustom.java

import com.example.cafe_demo.entity.Product;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> findByOwnerPhoneNumberWithCursor(String phoneNumber, Long cursorId, int size);

    List<Product> findByOwnerPhoneNumberAndNameOrInitial(String phoneNumber, String search);
}