package com.app.expenseautomator.security;

import java.util.Date;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
    
    private final SecretKey key = Keys.hmacShaKeyFor(
        "secret-key-256-bytes-to-generate-access-token".getBytes()
    );

    public JwtService() {
        
    }

    public String generateToken(UserDetails uDetails) {
        return Jwts.builder()
        .subject(uDetails.getUsername())
        .issuedAt(new Date())
        .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60))
        .signWith(key)
        .compact();
    }

    private <T>T extractClaims(String token, Function<Claims, T> resolver) {
        var payload = Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload();

        return resolver.apply(payload);
    }

    public String extractUserName(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = extractUserName(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    public Date getExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }
}
