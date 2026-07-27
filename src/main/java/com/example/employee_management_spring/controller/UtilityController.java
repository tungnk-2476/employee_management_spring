package com.example.employee_management_spring.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management_spring.service.UtilityService;

@RestController
@RequestMapping("/api/lab")
public class UtilityController {
    private final UtilityService utilityService;

    public UtilityController(UtilityService utilityService) {
        this.utilityService = utilityService;
    }

    @GetMapping("/format-name")
    public Map<String, String> formatName(@RequestParam String name) {
        return Map.of(
                "originalName", name,
                "formattedName", utilityService.formatName(name));
    }

    @GetMapping("/employee-code")
    public Map<String, String> generateEmployeeCode() {
        return Map.of(
                "employeeCode", utilityService.generateEmployeeCode());
    }
}
