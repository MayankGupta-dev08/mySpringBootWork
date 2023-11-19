package com.dev.mayang.restwebapp.rest;

import com.dev.mayang.restwebapp.dao.EmployeeDAO;
import com.dev.mayang.restwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // expose "/employees" for GET all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.getAllEntities();
    }
}