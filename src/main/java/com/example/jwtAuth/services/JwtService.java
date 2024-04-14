package com.example.jwtAuth.services;

import com.example.jwtAuth.entities.User;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    public String extractUsername(String token);

    public Claims extractAllClaims(String token);

    public String generateToken(UserDetails userDetails);

    public String generateToken(Map<String, Object> extraClaims,UserDetails userDetails);

    public boolean isTokenValid(String token, UserDetails userDetails);
}
