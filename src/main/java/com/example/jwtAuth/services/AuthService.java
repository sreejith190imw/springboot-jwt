package com.example.jwtAuth.services;

import com.example.jwtAuth.requests.RegisterRequest;
import com.example.jwtAuth.responses.AuthenticationResponse;
import com.example.jwtAuth.responses.LoginRequest;

public interface AuthService {

    AuthenticationResponse register(RegisterRequest request);
    AuthenticationResponse login(LoginRequest request);
}
