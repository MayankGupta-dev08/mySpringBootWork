package dev.mayank.employee.app.service;

import dev.mayank.employee.app.dao.EmployeeRepository;
import dev.mayank.employee.app.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int theId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(theId);
        Employee employee = null;

        if (employeeOptional.isPresent())
            employee = employeeOptional.get();
        else
            throw new NoSuchElementException("Did not find employee id - " + theId);

        return employee;
    }

    @Override
    public Employee save(Employee theEmployee) {
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(int theId) {
        employeeRepository.deleteById(theId);
    }
}