package com.example.cafe_demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TokenStoreService {

    private final StringRedisTemplate redisTemplate;

    @Autowired
    public TokenStoreService(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void storeToken(String phoneNumber, String refreshToken, long expirationTimeInSeconds) {
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(phoneNumber, refreshToken, expirationTimeInSeconds, TimeUnit.SECONDS);
    }

    public void invalidateToken(String phoneNumber) {
        redisTemplate.delete(phoneNumber);
    }

    public String getRefreshToken(String phoneNumber) {
        return redisTemplate.opsForValue().get(phoneNumber);
    }
}
