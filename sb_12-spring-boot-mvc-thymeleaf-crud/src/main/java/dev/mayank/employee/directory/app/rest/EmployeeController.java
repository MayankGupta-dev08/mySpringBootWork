package dev.mayank.employee.directory.app.rest;

import dev.mayank.employee.directory.app.entity.Employee;
import dev.mayank.employee.directory.app.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@AllArgsConstructor
@SuppressWarnings("unused")
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * @param model
     * @return employeeListPage.html
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/list">List all employees</a>
     */
    @GetMapping("/list")
    public String listAllEmployees(Model model) {
        List<Employee> employeeList = employeeService.findAll();
        model.addAttribute("employees", employeeList);
        return "employeeListPage";
    }

    /**
     * @param model
     * @return employeeFormPage.html
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/showFormForAddingEmployee">Add new employee form</a>
     */
    @GetMapping("/showFormForAddingEmployee")
    public String showNewEmployeeForm(Model model) {
        model.addAttribute("newEmployee", new Employee());
        return "employeeFormPage";
    }

    /**
     * @param employee
     * @return redirect:/list (employeeListPage.html)
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/save">Save an employee</a>
     */
    @PostMapping("/save")
    public String saveAnEmployee(@ModelAttribute("newEmployee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/api/v1/employees/list";
    }

    /* @GetMapping("/employees/{employeeId}")
    public Employee findById(@PathVariable int employeeId) {
        return employeeService.findById(employeeId);
    }

    @DeleteMapping("/employees/{employeeId}")
    public void deleteAll(@PathVariable int employeeId) {
        employeeService.deleteById(employeeId);
    }

    @DeleteMapping("/employees")
    public void deleteAll() {
        employeeService.deleteAll();
    }

    @PutMapping("/employees")
    public String update(@RequestBody Employee employee) {
        return employeeService.updateEntity(employee);
    } */
}