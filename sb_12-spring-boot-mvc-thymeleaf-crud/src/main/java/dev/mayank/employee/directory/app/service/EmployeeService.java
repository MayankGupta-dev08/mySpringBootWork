package dev.mayank.employee.directory.app.service;

import dev.mayank.employee.directory.app.dao.EmployeeRepository;
import dev.mayank.employee.directory.app.entity.Employee;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmployeeService implements ServiceI<Employee> {
    private final EmployeeRepository employeeRepository;

    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAllByOrderByFirstNameAsc();
    }

    @Override
    public Employee findById(int id) {
        Optional<Employee> emp_optional = employeeRepository.findById(id);
        Employee employee = isEmployeeNull(emp_optional, String.format("Invalid operation! Could not find Employee with id=%s.", id));
        System.out.println("Found the detail: " + employee.toString());
        return employee;
    }

    /**
     * @param employee if `employee.id` is already existing, then it will update it, rather than saving a new entity.
     */
    @Override
    @Transactional
    public Employee save(Employee employee) {
        Optional<Employee> emp_opt = Optional.of(employee);
        Employee nonNullEmployee = isEmployeeNull(emp_opt, "Invalid operation: Send valid Employee entity!");
        return employeeRepository.save(nonNullEmployee);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        System.out.printf("Deleting employee with id: %s...%n", id);
        employeeRepository.deleteById(id);
    }

    private Employee isEmployeeNull(Optional<Employee> emp_optional, String message) {
        if (emp_optional.isEmpty()) {
            System.err.println(message);
            throw new NoSuchElementException(message);
        }
        return emp_optional.get();
    }
}