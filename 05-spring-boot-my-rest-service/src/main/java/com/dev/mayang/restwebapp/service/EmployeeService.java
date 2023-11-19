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
        Employee employee = employeeDAO.getEntityById(id);
        if (employee == null)
            throw new RuntimeException(String.format("Invalid operation! Could not find Employee with id=%s.", id));

        System.out.println("Found the detail: " + employee.toString());
        return employee;
    }

    // POST
    @Override
    @Transactional
    public String save(Employee employee) {
        if (employee == null)
            throw new RuntimeException("Invalid operation: Send valid Employee entity!");

        return employeeDAO.postEntity(employee);
    }

    // DELETE by id
    @Override
    @Transactional
    public String deleteById(int id) {
        Employee employeeById = findById(id);
        System.out.println(String.format("Deleting employee with id: %s...", id));
        return employeeDAO.deleteEntityById(employeeById);
    }

    // DELETE all
    @Override
    @Transactional
    public String deleteAll() {
        return employeeDAO.deleteAllEntities();
    }
}