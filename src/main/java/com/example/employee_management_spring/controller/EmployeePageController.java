package com.example.employee_management_spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.employee_management_spring.model.EmployeeRequest;
import com.example.employee_management_spring.service.DepartmentService;
import com.example.employee_management_spring.service.EmployeeService;
import com.example.employee_management_spring.service.ReportService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/employees")
public class EmployeePageController {
    private final EmployeeService employeeService;
    private final DepartmentService departmentService;
    private final ReportService reportService;

    public EmployeePageController(
            EmployeeService employeeService,
            DepartmentService departmentService,
            ReportService reportService) {
        this.employeeService = employeeService;
        this.departmentService = departmentService;
        this.reportService = reportService;
    }

    @GetMapping("/list")
    public String showEmployeeList(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String department,
            Model model) {
        model.addAttribute(
                "employees",
                employeeService.search(name, department));
        model.addAttribute("departments", departmentService.findAll());
        model.addAttribute("name", name);
        model.addAttribute("department", department);

        return "employees/list";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("employeeRequest", new EmployeeRequest());
        model.addAttribute("departments", departmentService.findAll());

        return "employees/add";
    }

    @PostMapping("/add")
    public String createEmployee(
            @Valid @ModelAttribute("employeeRequest") EmployeeRequest request,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("departments", departmentService.findAll());

            return "employees/add";
        }

        employeeService.create(request);

        redirectAttributes.addFlashAttribute(
                "message",
                "Employee created successfully");

        return "redirect:/employees/list";
    }

    @GetMapping("/statistics")
    public String showStatistics(Model model) {
        model.addAttribute(
                "departmentStats",
                reportService.getEmployeeCountByDepartment());
        model.addAttribute(
                "totalEmployees",
                reportService.getTotalEmployeeCount());

        return "employees/statistics";
    }
}
