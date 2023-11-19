package com.dev.mayang.restwebapp.service;

import com.dev.mayang.restwebapp.dao.EmployeeDAO;
import com.dev.mayang.restwebapp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeService implements ServiceI<Employee> {

    private EmployeeDAO employeeDAO;

    @Autowired
    public EmployeeService(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // GET all
    @Override
    public List<Employee> findAll() {
        return employeeDAO.getAllEntities();
    }

    // GET by id
    @Override
    public Employee findById(int id) {
        Employee employeeWithId = employeeDAO.getEntityById(id);
        if (employeeWithId != null) {
            System.out.println("Found the detail: " + employeeWithId.toString());
            return employeeWithId;
        }
        System.out.println(String.format("Could not find Employee with id:%s", id));
        return null;
    }

    // POST
    @Override
    @Transactional
    public void save(Employee employee) {
        if (employee == null) {
            System.out.println("Invalid operation: Send valid Employee entity!");
            return;
        }
        employeeDAO.postEntity(employee);
    }
}