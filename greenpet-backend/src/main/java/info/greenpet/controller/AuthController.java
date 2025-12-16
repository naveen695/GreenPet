package info.greenpet.controller;

import info.greenpet.dto.AuthRequests.*;
import info.greenpet.dto.AuthResponses.AuthResponse;
import info.greenpet.model.User;
import info.greenpet.security.JwtUtil;
import info.greenpet.service.EmailService;
import info.greenpet.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtUtil;
    private final EmailService emailService;

    @Autowired
    public AuthController(UserService userService, JwtUtil jwtUtil, EmailService emailService) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest req) {
        if (userService.findByEmail(req.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Map.of("error", "Email already registered"));
        }
        User u = new User();
        u.setName(req.getName());
        u.setEmail(req.getEmail());
        User saved = userService.register(u, req.getPassword());

        // send welcome email (consider async)
        try {
            emailService.sendRegistrationEmail(saved);
        } catch (Exception e) {
            // log and continue; do not block registration on mail failure
        }

        String token = jwtUtil.generateToken(saved.getEmail());
        return ResponseEntity.ok(new AuthResponse(token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest req) {
        return userService.findByEmail(req.getEmail())
                .filter(u -> userService.checkPassword(req.getPassword(), u.getPasswordHash()))
                .map(u -> ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u.getEmail()))))
                .orElseGet(() -> ResponseEntity.status(401).body(Map.of("error", "Invalid credentials")));
    }

    @GetMapping("/ping")
    public ResponseEntity<?> ping() {
        return ResponseEntity.ok(Map.of("ok", true));
    }
}
