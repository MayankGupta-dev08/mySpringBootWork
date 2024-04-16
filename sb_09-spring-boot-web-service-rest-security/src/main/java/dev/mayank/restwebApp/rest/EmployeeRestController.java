package dev.mayank.restwebApp.rest;

import dev.mayank.restwebApp.entity.Employee;
import dev.mayank.restwebApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class EmployeeRestController {

    // Constructor injection --> Constructor using ProjectLombok
    private final EmployeeService employeeService;

    // expose "/employees" for GET all employees
    @SuppressWarnings("unused")
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    // expose "/employees/{employeeId}" for GET single employees using id
    @SuppressWarnings("unused")
    @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    // expose "/employees" for POST an employee
    @SuppressWarnings("unused")
    @PostMapping("/employees")
    public Employee save(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    // expose "/employees" for DELETE an employee
    @SuppressWarnings("unused")
    @DeleteMapping("/employees/{employeeId}")
    public void deleteAll(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
    }

    // expose "/employees" for DELETE all employees
    @SuppressWarnings("unused")
    @DeleteMapping("/employees")
    public void deleteAll() {
        employeeService.deleteAll();
    }

    // expose "/employees" for PUT an employee
    @SuppressWarnings("unused")
    @PutMapping("/employees")
    public String update(@RequestBody Employee employee) {
        return employeeService.updateEntity(employee);
    }
}