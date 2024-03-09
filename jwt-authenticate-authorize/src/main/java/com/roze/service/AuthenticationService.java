package com.roze.service;

import com.roze.config.JwtService;
import com.roze.entity.*;
import com.roze.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest registerRequest) {
        User user = User.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        String s = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(s)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticateRequest authenticateRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticateRequest.getEmail(),
                authenticateRequest.getPassword()));
        User user = userRepository.findByEmail(authenticateRequest.getEmail()).orElseThrow();
        String s = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(s)
                .build();
    }
}
