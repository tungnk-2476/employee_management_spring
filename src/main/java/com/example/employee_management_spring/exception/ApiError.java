package com.example.employee_management_spring.exception;

import java.time.Instant;
import java.util.Map;

public record ApiError(Instant timestamp,
        int status,
        String error,
        String message,
        Map<String, String> fieldErrors) {
}