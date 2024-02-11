package com.example.cafe_demo.service;
import com.example.cafe_demo.component.JwtTokenProvider;
import com.example.cafe_demo.entity.Owner;
import com.example.cafe_demo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OwnerService {

    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;
    public Owner registerOwner(String phoneNumber, String password) {
        if (!isValidPhoneNumber(phoneNumber)) {
            throw new IllegalArgumentException("Invalid phone number format");
        }
        Owner owner = new Owner();
        owner.setPhoneNumber(phoneNumber);
        owner.setPassword(passwordEncoder.encode(password));
        owner.setRole("ROLE_USER"); // 사용자 역할을 "USER"로 설정
        return ownerRepository.save(owner);
    }


    public boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("^\\d{10,11}$");
    }
}