package com.example.employee_management_spring.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.employee_management_spring.model.Department;
import com.example.employee_management_spring.repository.DepartmentRepository;

@Service
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    public List<Department> findAll() {
        return departmentRepository.findAll();
    }
}
