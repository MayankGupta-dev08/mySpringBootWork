package dev.mayank.myRESTfulApp.service;

import dev.mayank.myRESTfulApp.dao.EmployeeRepository;
import dev.mayank.myRESTfulApp.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements ServiceI<Employee> {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // GET all
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    // GET by id
    @Override
    public Employee findById(int id) {
        Optional<Employee> emp_optional = employeeRepository.findById(id);
        Employee employee = isEmployeeNull(emp_optional, String.format("Invalid operation! Could not find Employee with id=%s.", id));
        System.out.println("Found the detail: " + employee.toString());
        return employee;
    }

    // POST
    @Override
    @Transactional
    public Employee save(Employee employee) {
        Optional<Employee> emp_opt = Optional.of(employee);
        Employee nonNullEmployee = isEmployeeNull(emp_opt, "Invalid operation: Send valid Employee entity!");

        // To make sure that this obj is used for saving a new entity
        // and not for updating any already existing one, if by any chance the user sends any id in the json payload.
        nonNullEmployee.setId(0);
        return employeeRepository.save(nonNullEmployee);
    }

    // DELETE by id
    @Override
    @Transactional
    public void deleteById(int id) {
        System.out.printf("Deleting employee with id: %s...%n", id);
        employeeRepository.deleteById(id);
    }

    // DELETE all
    @Override
    @Transactional
    public void deleteAll() {
        employeeRepository.deleteAll();
    }

    // PUT
    @Override
    @Transactional
    public String updateEntity(Employee new_employee) {
        Optional<Employee> optionalEmployee = Optional.of(new_employee);
        Employee nN_employee = isEmployeeNull(optionalEmployee, "Invalid operation: Send valid Employee entity!");
        int id = nN_employee.getId();
        Employee old_employee = findById(id);

        Employee old_emp = new Employee(old_employee.getFirstName(), old_employee.getLastName(), old_employee.getEmail());
        old_emp.setId(id);

        Employee employee = employeeRepository.save(nN_employee);
        return String.format("Updated %s --> %s", old_emp, employee);
    }

    private Employee isEmployeeNull(Optional<Employee> emp_optional, String message) {
        if (emp_optional.isEmpty())
            throw new RuntimeException(message);

        return emp_optional.get();
    }
}