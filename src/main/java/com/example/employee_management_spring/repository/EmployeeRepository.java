package com.example.employee_management_spring.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management_spring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    @EntityGraph(attributePaths = "department")
    List<Employee> findAll();

    @EntityGraph(attributePaths = "department")
    List<Employee> findByNameContainingIgnoreCase(String name);

    @EntityGraph(attributePaths = "department")
    List<Employee> findByDepartment_NameContainingIgnoreCase(String departmentName);
}
