package dev.mayank.restWebApp.service;

import dev.mayank.restWebApp.dao.EmployeeDAO;
import dev.mayank.restWebApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>In the Service layer, we will inject the DAOs which are needed. Here, only one.</p>
 * Here we are using `@Transactional` on methods for POST, PUT, and DELETE.
 */
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
        isEmployeeNull(employee, String.format("Invalid operation! Could not find Employee with id=%s.", id));
        System.out.println("Found the detail: " + employee.toString());
        return employee;
    }

    // POST
    @Override
    @Transactional
    public String save(Employee employee) {
        isEmployeeNull(employee, "Invalid operation: Send valid Employee entity!");
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

    // PUT
    @Override
    @Transactional
    public String updateEntity(Employee new_employee) {
        isEmployeeNull(new_employee, "Invalid operation: Send valid Employee entity!");
        int id = new_employee.getId();
        Employee old_employee = findById(id);

        Employee old_emp = new Employee(old_employee.getFirstName(), old_employee.getLastName(), old_employee.getEmail());
        old_emp.setId(id);

        Employee employee = employeeDAO.updateEntity(new_employee);
        return String.format("Updated %s --> %s", old_emp, employee);
    }

    private void isEmployeeNull(Employee employee, String message) {
        if (employee == null)
            throw new RuntimeException(message);
    }
}