package com.example.cafe_demo.component;
import com.example.cafe_demo.service.TokenStoreService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.Authentication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtTokenProvider jwtTokenProvider;
    @Autowired
    private TokenStoreService tokenStoreService;

    // Constructor
    public JwtAuthenticationFilter(JwtTokenProvider jwtTokenProvider, TokenStoreService tokenStoreService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.tokenStoreService = tokenStoreService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = getTokenFromRequest(request);
        if (token != null && jwtTokenProvider.validateToken(token)) {
            String phoneNumber = jwtTokenProvider.getPhoneNumberFromToken(token);
            // Here, you might want to fetch more details about the user (e.g., roles) if needed
            Authentication auth = new UsernamePasswordAuthenticationToken(phoneNumber, null, Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        // Paths that should not be filtered
        String[] permitAllEndpointList = {
                "/api/auth/login",
                "/api/auth/register",
                "/owner/**"
        };


        for (String endpoint : permitAllEndpointList) {
            if (new AntPathMatcher().match(endpoint, request.getRequestURI())) {
                return true;
            }
        }
        return false;
    }

}
