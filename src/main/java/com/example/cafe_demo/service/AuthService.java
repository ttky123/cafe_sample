package com.example.cafe_demo.service;

import com.example.cafe_demo.component.JwtTokenProvider;
import com.example.cafe_demo.entity.Owner;
import com.example.cafe_demo.repository.OwnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class AuthService {
    @Autowired
    private OwnerRepository ownerRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenStoreService tokenStoreService;


 /*   public String login(String phoneNumber, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Owner owner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with phone number: " + phoneNumber));

        return jwtTokenProvider.createToken(phoneNumber);
    }*/

    public Map<String, String> login(String phoneNumber, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Owner owner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with phone number: " + phoneNumber));

        // 액세스 토큰 생성
        String accessToken = jwtTokenProvider.createToken(phoneNumber);
        // 리프레시 토큰 생성
        String refreshToken = jwtTokenProvider.createRefreshToken(phoneNumber);
        // 리프레시 토큰을 Redis에 저장
        tokenStoreService.storeToken(phoneNumber, refreshToken, JwtTokenProvider.REFRESH_TOKEN_EXPIRE_TIME);

        // 토큰 반환
        Map<String, String> tokens = new HashMap<>();
        tokens.put("accessToken", accessToken);
        tokens.put("refreshToken", refreshToken);
        return tokens;
    }

    public void logout(String phoneNumber) {
        // Redis에서 리프레시 토큰 무효화
        tokenStoreService.invalidateToken(phoneNumber);
    }
}

