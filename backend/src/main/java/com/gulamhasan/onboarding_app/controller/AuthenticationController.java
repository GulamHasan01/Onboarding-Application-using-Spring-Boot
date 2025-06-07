package com.gulamhasan.onboarding_app.controller;

import com.gulamhasan.onboarding_app.model.User;
import com.gulamhasan.onboarding_app.security.JwtUtil;
import com.gulamhasan.onboarding_app.service.UserService;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class AuthenticationController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationController(UserService userService, AuthenticationManager authenticationManager, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
    }

    static class RegisterRequest {
        public String name;
        public String email;
        public String password;
    }

    static class LoginRequest {
        public String email;
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        if (userService.emailExists(request.email)) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already exists"));
        }

        User user = new User();
        user.setName(request.name);
        user.setEmail(request.email);
        user.setPassword(request.password); // raw password, encoding handled in service

        User savedUser = userService.registerUser(user);
        return ResponseEntity.ok(Map.of("message", "User registered successfully"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.email, request.password)
            );

            String token = JwtUtil.generateToken(request.email);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (AuthenticationException ex) {
            return ResponseEntity.status(401).body(Map.of("error", "Invalid credentials"));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> profile(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(401).body(Map.of("error", "Missing or invalid Authorization header"));
            }

            String token = authHeader.substring(7);
            if (!JwtUtil.validateToken(token)) {
                return ResponseEntity.status(401).body(Map.of("error", "Invalid token"));
            }

            String email = JwtUtil.extractEmail(token);
            Optional<User> userOpt = userService.findByEmail(email);

            if (userOpt.isPresent()) {
                User user = userOpt.get();
                user.setPassword(null); // Hide password before sending
                return ResponseEntity.ok(user);
            }

            return ResponseEntity.status(404).body(Map.of("error", "User not found"));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(Map.of("error", "Error fetching profile"));
        }
    }
}
