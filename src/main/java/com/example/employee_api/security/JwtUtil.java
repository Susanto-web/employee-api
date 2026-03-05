package com.example.employee_api.security;

import org.springframework.stereotype.Component;
import java.util.*;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.*;

@Component
public class JwtUtil {

    private final String SECRET = "jwt-secret-key-123456";
    private final long EXPIRATION = 86400000;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION))
                .signWith(Keys.hmacShaKeyFor(SECRET.getBytes()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET.getBytes())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}