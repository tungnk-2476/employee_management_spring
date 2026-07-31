package com.example.employee_management_spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.employee_management_spring.model.DepartmentEmployeeCount;
import com.example.employee_management_spring.repository.EmployeeRepository;

@Service
public class ReportService {
    private static final Logger log = LoggerFactory.getLogger(ReportService.class);

    private final EmployeeRepository employeeRepository;

    public ReportService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Cacheable(cacheNames = "employee-count", key = "'total'")
    public long getTotalEmployeeCount() {
        log.info("Cache miss: querying total employee count from database");

        return employeeRepository.count();
    }

    public List<DepartmentEmployeeCount> getEmployeeCountByDepartment() {
        return employeeRepository.countEmployeesByDepartment();
    }
}
