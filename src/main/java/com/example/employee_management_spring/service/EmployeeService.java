package com.example.employee_management_spring.service;

import java.util.List;
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

        return employeeRepository.save(employee);
    }

    public List<Employee> searchByName(String name) {
        return employeeRepository.findByNameContainingIgnoreCase(name);
    }

    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    public Employee findById(int id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee", id));
    }

    public List<Employee> searchByDepartment(String departmentName) {
        return employeeRepository
                .findByDepartment_NameContainingIgnoreCase(departmentName);
    }

    public Employee update(int id, EmployeeRequest request) {
        Employee employee = findById(id);
        Department department = findDepartmentById(request.getDepartmentId());

        employee.setName(request.getName());
        employee.setEmail(request.getEmail());
        employee.setDepartment(department);

        return employeeRepository.save(employee);
    }

    public void deleteById(int id) {
        if (!employeeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Employee", id);
        }

        employeeRepository.deleteById(id);
    }

    private Department findDepartmentById(int departmentId) {
        return departmentRepository.findById(departmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Department", departmentId));
    }
}
