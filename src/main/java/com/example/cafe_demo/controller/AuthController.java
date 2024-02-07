package com.example.cafe_demo.controller;

import com.example.cafe_demo.component.JwtTokenProvider;
import com.example.cafe_demo.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private OwnerService ownerService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestParam String phoneNumber, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(phoneNumber, password));
        String token = jwtTokenProvider.createToken(phoneNumber);
        return ResponseEntity.ok(token);
    }
}