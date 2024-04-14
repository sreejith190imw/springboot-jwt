package com.example.jwtAuth.services.Impl;

import com.example.jwtAuth.entities.User;
import com.example.jwtAuth.enums.Role;
import com.example.jwtAuth.repositories.UserRepository;
import com.example.jwtAuth.requests.RegisterRequest;
import com.example.jwtAuth.responses.AuthenticationResponse;
import com.example.jwtAuth.responses.LoginRequest;
import com.example.jwtAuth.services.JwtService;
import com.example.jwtAuth.services.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        User newUser = userRepository.save(user);
        String token = jwtService.generateToken(newUser);

        return AuthenticationResponse
                .builder()
                .token(token)
                .build();
    }

    @Override
    public AuthenticationResponse login(LoginRequest request) {
        try{
            var auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
            User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
            String token = jwtService.generateToken(user);
            return AuthenticationResponse
                   .builder()
                   .token(token)
                   .build();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
