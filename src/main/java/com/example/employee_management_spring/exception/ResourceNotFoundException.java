package com.example.employee_management_spring.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String resourceName, Integer id) {
        super(resourceName + " not found with id: " + id);
    }
}
