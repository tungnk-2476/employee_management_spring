package com.example.employee_management_spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableCaching
public class EmployeeManagementSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementSpringApplication.class, args);
	}

}
