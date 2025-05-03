package com.OrderFlowAPI.OrderFlowAPI.util;

import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.OrderFlowAPI.OrderFlowAPI.dto.UserDto;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private Key secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 6; // 6 hours

    @PostConstruct
    public void init() {
        byte[] keyBytes = Base64.getDecoder().decode(SECRET_KEY); // Decode the Base64 key
        this.secretKey = Keys.hmacShaKeyFor(keyBytes); // Use pure bytes
    }

    public String generateJWT(UserDto userDto) {
        Map<String, Object> headers = new HashMap<>();

        headers.put("alg", "HS256");
        headers.put("typ", "JWT");

        return Jwts.builder()
                .setHeader(headers)
                .setSubject(userDto.getEmail())
                .claim("id", userDto.getUserId())
                .claim("role", userDto.getRoleDto().getName().toUpperCase())
                .setIssuedAt(new Date())
                // Set the expiration date (6 hours)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

    public boolean isTokenExpired(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration()
                .before(new Date());
    }

    public String extractEmail(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public String extractRole(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(secretKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);
    }
}
