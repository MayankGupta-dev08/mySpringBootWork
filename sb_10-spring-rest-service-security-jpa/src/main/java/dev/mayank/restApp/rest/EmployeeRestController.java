package dev.mayank.restApp.rest;

import dev.mayank.restApp.entity.Employee;
import dev.mayank.restApp.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@SuppressWarnings("unused")
public class EmployeeRestController {

    private EmployeeService employeeService;

    /**
     * expose "/employees" and return a list of employees
     */
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }

    /**
     * add mapping for GET /employees/{employeeId}
     */
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee theEmployee = employeeService.findById(employeeId);
        if (theEmployee == null)
            throw new RuntimeException("Employee id not found - " + employeeId);
        return theEmployee;
    }

    /**
     * add mapping for POST /employees - add new employee
     * <p>also just in case they pass an id in JSON ... set id to 0</p>
     * this is to force a save of new item ... instead of update
     */
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee) {
        theEmployee.setId(0);
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    /**
     * add mapping for PUT /employees - update existing employee
     */
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee) {
        Employee dbEmployee = employeeService.save(theEmployee);
        return dbEmployee;
    }

    /**
     * add mapping for DELETE /employees/{employeeId} - delete employee
     */
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);
        if (tempEmployee == null)
            throw new RuntimeException("Employee id not found - " + employeeId);

        employeeService.deleteById(employeeId);
        return "Deleted employee id - " + employeeId;
    }
}