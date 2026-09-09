package com.app.expenseautomator.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.expenseautomator.dtos.auth.JwtResponse;
import com.app.expenseautomator.dtos.auth.LoginRequest;
import com.app.expenseautomator.security.JwtService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    private final AuthenticationManager authManager;
    private final UserDetailsService uDetailsService;
    private final JwtService jwtService;

    public AuthenticationController(AuthenticationManager authManager, UserDetailsService uDetailsService, JwtService jwtService) {
        this.authManager = authManager;
        this.uDetailsService = uDetailsService;
        this.jwtService = jwtService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        String email = request.getEmail();
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(email, request.getPassword())
        );

        UserDetails uDetails = uDetailsService.loadUserByUsername(email);
        String token = jwtService.generateToken(uDetails);

        return ResponseEntity.ok(new JwtResponse(token, jwtService.getExpiration(token)));
    }
}
