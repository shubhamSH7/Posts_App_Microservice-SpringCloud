package com.posts.entity;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class TokenService {

    private final HttpServletRequest request;

    public TokenService(HttpServletRequest request) {
        this.request = request;
    }

    public String getJwtToken() {
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            return authorizationHeader.substring(7);
        }
        return null;
    }
}
