package com.example.cafe_demo.controller;

import com.example.cafe_demo.entity.Owner;
import com.example.cafe_demo.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Owner owner) {
        Map<String, String> tokens = authService.login(owner.getPhoneNumber(), owner.getPassword());
        return ResponseEntity.ok(tokens);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestParam String phoneNumber) {
        authService.logout(phoneNumber);
        return ResponseEntity.ok().build();
    }
}
