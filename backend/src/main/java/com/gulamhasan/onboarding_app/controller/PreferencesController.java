package com.gulamhasan.onboarding_app.controller;

import com.gulamhasan.onboarding_app.model.Preferences;
import com.gulamhasan.onboarding_app.model.User;
import com.gulamhasan.onboarding_app.service.PreferencesService;
import com.gulamhasan.onboarding_app.service.UserService;
import com.gulamhasan.onboarding_app.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/preferences")
public class PreferencesController {

    private final PreferencesService preferencesService;
    private final UserService userService;

    public PreferencesController(PreferencesService preferencesService, UserService userService) {
        this.preferencesService = preferencesService;
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<?> getPreferences(HttpServletRequest request) {
        try {
            String token = extractToken(request);
            String email = JwtUtil.extractEmail(token);
            User user = userService.getUserByEmail(email);
            Optional<Preferences> prefs = preferencesService.getPreferencesByUser(user);

            return ResponseEntity.ok(prefs.orElse(new Preferences()));
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token or error fetching preferences");
        }
    }

    @PostMapping
    public ResponseEntity<?> saveOrUpdatePreferences(HttpServletRequest request, @RequestBody Preferences preferences) {
        try {
            String token = extractToken(request);
            String email = JwtUtil.extractEmail(token);
            User user = userService.getUserByEmail(email);

            Preferences savedPrefs = preferencesService.saveOrUpdatePreferences(user, preferences);
            return ResponseEntity.ok(savedPrefs);
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid token or error saving preferences");
        }
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Missing or invalid Authorization header");
        }
        return authHeader.substring(7);
    }
}
