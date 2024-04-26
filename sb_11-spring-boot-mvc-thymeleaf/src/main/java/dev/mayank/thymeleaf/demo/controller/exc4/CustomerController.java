package dev.mayank.thymeleaf.demo.controller.exc4;

import dev.mayank.thymeleaf.demo.model.Customer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exercise4: Form Validation and Required Fields using Spring MVC
 */
@Controller
@RequestMapping("customers/")
@SuppressWarnings("unused")
public class CustomerController {

    /**
     * controller method @<a href="http://localhost:8080/customers/showCustomerForm">showCustomerForm</a>
     * to show the initial HTML form.
     */
    @GetMapping("/showCustomerForm")
    public String showForm(Model model) {
        return "customerSignupForm";
    }

    /**
     * controller method @<a href="http://localhost:8080/customers/processCustomerForm">processCustomerForm</a>
     * to process the HTML form.
     */
    @PostMapping("/processCustomerForm")
    public String processForm(@ModelAttribute("customer") Customer customer) {
        System.out.println(customer);    //logging the data
        return "customerConfirmationForm";
    }
}