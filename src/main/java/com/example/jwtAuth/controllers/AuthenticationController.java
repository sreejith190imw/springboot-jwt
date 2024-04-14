package com.example.jwtAuth.controllers;

import com.example.jwtAuth.requests.RegisterRequest;
import com.example.jwtAuth.responses.AuthenticationResponse;
import com.example.jwtAuth.responses.LoginRequest;
import com.example.jwtAuth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register (
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login (
            @RequestBody LoginRequest request
    ){
        return ResponseEntity.ok(authService.login(request));
    }
}
