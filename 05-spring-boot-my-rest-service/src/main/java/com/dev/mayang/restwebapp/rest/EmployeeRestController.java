package com.dev.mayang.restwebapp.rest;

import com.dev.mayang.restwebapp.entity.Employee;
import com.dev.mayang.restwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired  // constructor injection for EmployeeService
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // expose "/employees" for GET all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }
}