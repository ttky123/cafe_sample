package com.example.cafe_demo.repository;

import com.example.cafe_demo.entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OwnerRepository extends JpaRepository<Owner, Long> {
    Owner findByPhoneNumber(String phoneNumber);
}