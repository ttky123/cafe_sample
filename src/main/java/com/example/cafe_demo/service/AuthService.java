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

    // 리프레시 토큰 저장소 - 실제 환경에서는 데이터베이스나 Redis 사용
    private Map<String, String> refreshTokenStore = new ConcurrentHashMap<>();

    public String login(String phoneNumber, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Owner owner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with phone number: " + phoneNumber));

        return jwtTokenProvider.createToken(phoneNumber, owner.getId()); // 예제에서는 Owner ID를 사용하는 버전으로 변경
    }


    public void logout(String phoneNumber) {
        // 리프레시 토큰 무효화
        refreshTokenStore.remove(phoneNumber);
    }
    public String login(String phoneNumber, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(phoneNumber, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Owner owner = ownerRepository.findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new IllegalArgumentException("User not found with phone number: " + phoneNumber));

        return jwtTokenProvider.createToken(phoneNumber); // 예제에서는 Owner ID를 사용하는 버전으로 변경
    }
    // 로그아웃 요청 처리를 위한 메서드 등 추가 구현...
}