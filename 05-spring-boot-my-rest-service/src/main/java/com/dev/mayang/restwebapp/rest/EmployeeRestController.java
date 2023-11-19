package com.dev.mayang.restwebapp.rest;

import com.dev.mayang.restwebapp.entity.Employee;
import com.dev.mayang.restwebapp.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    // expose "/employees/{employeeId}" for GET single employees using id
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    // expose "/employees" for POST an employee
    @PostMapping("/employees")
    public void save(@RequestBody Employee employee) {
        employeeService.save(employee);
    }
}