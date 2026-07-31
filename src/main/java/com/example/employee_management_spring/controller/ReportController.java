package com.example.employee_management_spring.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.employee_management_spring.model.DepartmentEmployeeCount;
import com.example.employee_management_spring.service.ReportService;

@RestController
@RequestMapping("/api/reports")
public class ReportController {
    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @GetMapping("/employees/count")
    public ResponseEntity<Map<String, Long>> getEmployeeCount() {
        return ResponseEntity.ok(
                Map.of(
                        "totalEmployees",
                        reportService.getTotalEmployeeCount()));
    }

    @GetMapping("/employees/by-department")
    public ResponseEntity<List<DepartmentEmployeeCount>> getEmployeesByDepartment() {
        return ResponseEntity.ok(reportService.getEmployeeCountByDepartment());
    }
}
