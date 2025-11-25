package org.example.qissaproject.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.qissaproject.dto.AuthResponse;
import org.example.qissaproject.dto.LoginRequest;
import org.example.qissaproject.dto.RegisterRequest;
import org.example.qissaproject.service.AuthService;
import org.example.qissaproject.service.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final JwtService jwtService;
    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = service.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = service.authenticate(request);
        return ResponseEntity.ok(response);
    }
}