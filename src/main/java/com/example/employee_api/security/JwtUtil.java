package com.example.employee_api.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // SECRET minimal 32 chars agar valid untuk HS256
    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";

    // -------------------------------
    // Generate Key
    // -------------------------------
    private Key getKey() {
        return Keys.hmacShaKeyFor(SECRET.getBytes());
    }

    // -------------------------------
    // Generate Token
    // -------------------------------
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                // Token berlaku 1 hari (86400000 ms)
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // -------------------------------
    // Extract Username
    // -------------------------------
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // -------------------------------
    // Extract All Claims Safely
    // -------------------------------
    private Claims extractAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // -------------------------------
    // Check Token Expiration
    // -------------------------------
    private boolean isTokenExpired(String token) {
        Date expiration = extractAllClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    // -------------------------------
    // Validate Token
    // -------------------------------
    public boolean validateToken(String token, String username) {

        try {
            String extractedUsername = extractUsername(token);

            return (extractedUsername.equals(username) && !isTokenExpired(token));

        } catch (ExpiredJwtException e) {
            System.out.println("JWT Expired: " + e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println("Invalid JWT: " + e.getMessage());
        } catch (SignatureException e) {
            System.out.println("Invalid JWT Signature: " + e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println("Unsupported JWT: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("JWT claims string is empty: " + e.getMessage());
        }

        return false;
    }
}


//package com.example.employee_api.security;
//
//import io.jsonwebtoken.*;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.stereotype.Component;
//
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtUtil {
//
//    private final String SECRET = "mysecretkeymysecretkeymysecretkey12345";
//
//    private Key getKey() {
//        return Keys.hmacShaKeyFor(SECRET.getBytes());
//    }
//
//    public String generateToken(String username) {
//
//        return Jwts.builder()
//                .setSubject(username)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
//                .signWith(getKey(), SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    public String extractUsername(String token) {
//
//        return Jwts.parserBuilder()
//                .setSigningKey(getKey())
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    //public boolean validateToken(String jwt, String username) {
//    //}
//}
