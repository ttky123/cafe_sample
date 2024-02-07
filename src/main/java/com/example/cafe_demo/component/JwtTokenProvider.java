package com.example.cafe_demo.component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    private String secretKey = "36e0245edd425f60ff8c1fed1f8c52401ed2c058336fce087aa1bc4ec118f43e";

    // 토큰 생성
    public String createToken(String phoneNumber) {
        Claims claims = Jwts.claims().setSubject(phoneNumber);
        Date now = new Date();
        Date validity = new Date(now.getTime() + 3600000); // 1시간 유효

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    public String createRefreshToken(String phoneNumber) {
        Claims claims = Jwts.claims().setSubject(phoneNumber);
        Date now = new Date();
        Date validity = new Date(now.getTime() + 7 * 24 * 60 * 60 * 1000); // 리프레시 토큰 유효 기간: 7일

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    // 토큰에서 회원 정보 추출
    public String getPhoneNumberFromToken(String token) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody().getSubject();
    }
}