package com.example.employee_management_spring.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.employee_management_spring.model.DepartmentEmployeeCount;
import com.example.employee_management_spring.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Override
    @EntityGraph(attributePaths = "department")
    List<Employee> findAll();

    @Override
    @EntityGraph(attributePaths = "department")
    Optional<Employee> findById(Integer id);

    @EntityGraph(attributePaths = "department")
    List<Employee> findByNameContainingIgnoreCase(String name);

    @EntityGraph(attributePaths = "department")
    List<Employee> findByDepartment_NameContainingIgnoreCase(String departmentName);

    @Query("""
            SELECT new com.example.employee_management_spring.model.DepartmentEmployeeCount(
                e.department.id,
                e.department.name,
                COUNT(e.id)
            )
            FROM Employee e
            GROUP BY e.department.id, e.department.name
            ORDER BY e.department.name
            """)
    List<DepartmentEmployeeCount> countEmployeesByDepartment();
}
