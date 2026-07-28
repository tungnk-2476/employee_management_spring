package com.example.employee_management_spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.employee_management_spring.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

}
