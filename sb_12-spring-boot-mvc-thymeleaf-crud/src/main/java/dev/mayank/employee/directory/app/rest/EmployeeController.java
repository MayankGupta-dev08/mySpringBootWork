package dev.mayank.employee.directory.app.rest;

import dev.mayank.employee.directory.app.entity.Employee;
import dev.mayank.employee.directory.app.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/showFormForAddingEmployee">Add an employee</a>
     */
    @GetMapping("/showFormForAddingEmployee")
    public String showEmployeeFormForAdd(Model model) {
        model.addAttribute("theEmployee", new Employee());
        return "employeeFormPage";
    }

    /**
     * @param employee
     * @return redirect:/list (employeeListPage.html)
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/save">Save an employee</a>
     */
    @PostMapping("/save")
    public String saveAnEmployee(@ModelAttribute("theEmployee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/api/v1/employees/list";
    }

    /**
     * @param id
     * @param model
     * @return employeeFormPage.html
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/showFormForUpdatingEmployee">Update an employee</a>
     */
    @GetMapping("/showFormForUpdatingEmployee")
    public String showEmployeeFormForUpdate(@RequestParam("employeeId") int id, Model model) {
        Employee theEmployee = employeeService.findById(id);
        model.addAttribute("theEmployee", theEmployee);
        return "employeeFormPage";
    }

    /**
     * @param id
     * @return redirect:/list (employeeListPage.html)
     * @controller-method for <a href="http://localhost:8080/api/v1/employees/delete">Delete an employee</a>
     */
    @GetMapping("/delete")
    public String deleteAnEmployee(@RequestParam("employeeId") int id) {
        employeeService.deleteById(id);
        return "redirect:/api/v1/employees/list";
    }
}