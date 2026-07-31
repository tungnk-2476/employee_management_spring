package com.example.employee_management_spring.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management_spring.model.AppUser;
import com.example.employee_management_spring.model.RegisterRequest;
import com.example.employee_management_spring.service.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(
            @Valid @RequestBody RegisterRequest request) {
        AppUser user = authService.register(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Đăng ký thành công: " + user.getUsername());
    }
}
