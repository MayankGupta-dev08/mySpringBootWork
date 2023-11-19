package com.dev.mayang.restwebapp.service;

import com.dev.mayang.restwebapp.dao.EmployeeDAO;
import com.dev.mayang.restwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements ServiceI<Employee> {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    @Override
    public List<Employee> findAll() {
        return employeeDAO.getAllEntities();
    }
}