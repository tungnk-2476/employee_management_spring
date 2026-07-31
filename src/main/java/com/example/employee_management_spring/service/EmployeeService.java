package com.example.employee_management_spring.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.example.employee_management_spring.exception.ResourceNotFoundException;
import com.example.employee_management_spring.model.Department;
import com.example.employee_management_spring.model.Employee;
import com.example.employee_management_spring.model.EmployeeRequest;
import com.example.employee_management_spring.repository.DepartmentRepository;
import com.example.employee_management_spring.repository.EmployeeRepository;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private static final Logger log = LoggerFactory.getLogger(EmployeeService.class);

    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Employee create(EmployeeRequest request) {
        Department department = findDepartmentById(request.getDepartmentId());
        Employee employee = new Employee();
        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        log.info(
                "Created employee: id={}, departmentId={}",
                savedEmployee.getId(),
                department.getId());
        return savedEmployee;
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(Integer id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }

    public List<Employee> searchByDepartment(String departmentName) {
        return employeeRepository
                .findByDepartment_NameContainingIgnoreCase(departmentName);
    }

    public Employee update(Integer id, EmployeeRequest request) {
        Employee employee = findById(id);
        Department department = findDepartmentById(request.getDepartmentId());

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);

        log.info(
                "Updated employee: id={}, departmentId={}",
                savedEmployee.getId(),
                department.getId());

        return savedEmployee;
    }

    public void deleteById(Integer id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee", id);
        }

        employeeRepository.deleteById(id);
        log.info("Deleted employee: id={}", id);
    }

    public List<Employee> search(String name, String department) {
        if (name != null && !name.isBlank()) {
            return searchByName(name);
        }

        if (department != null && !department.isBlank()) {
            return searchByDepartment(department);
        }

        return findAll();
    }

    private Department findDepartmentById(Integer departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
    }
}
