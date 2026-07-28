package com.example.employee_management_spring.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.employee_management_spring.model.Employee;

@Service
public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    private int nextId = 1;

    public Employee create(Employee employee) {
        employee.setId(nextId++);
        employees.add(employee);
        return employee;
    }

    public List<Employee> searchByName(String name) {
        String keyword = name.toLowerCase();

        return employees.stream()
                .filter(employee -> employee.getName()
                        .toLowerCase()
                        .contains(keyword))
                .toList();
    }

    public List<Employee> findAll() {
        return List.copyOf(employees);
    }

    public Optional<Employee> findById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst();
    }
}
