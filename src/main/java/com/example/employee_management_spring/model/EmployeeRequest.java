package com.example.employee_management_spring.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {
    @NotBlank(message = "Name must not be blank")
    @Size(max = 100, message = "Name must contain at most 100 characters")
    private String name;
    @NotBlank(message = "Email must not be blank")
    @Email(message = "Email is not valid")
    @Size(max = 150, message = "Email must contain at most 150 characters")
    private String email;
    
    @NotNull(message = "Department is required")
    private int departmentId;

    public EmployeeRequest() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
