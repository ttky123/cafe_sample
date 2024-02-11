package com.example.cafe_demo.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfig {

    @Bean
    public JPAQueryFactory jpaQuery(EntityManager entityManager) {
        System.out.println(entityManager);
        return new JPAQueryFactory(entityManager);
    }
}