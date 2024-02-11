package com.example.cafe_demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;
@Entity
@Data
@RequiredArgsConstructor
public class Owner {
    @Id
    @NotNull
    private String phoneNumber; // 휴대폰 번호
    private String password; // 비밀번호
    private String role; // 사용자 역할
}