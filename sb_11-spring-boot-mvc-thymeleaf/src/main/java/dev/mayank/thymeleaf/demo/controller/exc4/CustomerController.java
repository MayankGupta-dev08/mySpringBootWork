package dev.mayank.thymeleaf.demo.controller.exc4;

import dev.mayank.thymeleaf.demo.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

/**
 * Exercise4: Form Validation and Required Fields using Spring MVC
 */
@Controller
@RequestMapping("customers/")
@SuppressWarnings("unused")
public class CustomerController {

    /**
     * controller method <a href="http://localhost:8080/customers/showCustomerForm">showCustomerForm</a>
     * to show the initial HTML form.
     */
    @GetMapping("/showCustomerForm")
    public String showForm(Model model) {
        model.addAttribute("customer", new Customer());
        return "customerSignupForm";
    }

    /**
     * controller method <a href="http://localhost:8080/customers/processCustomerForm">processCustomerForm</a>
     * to process the HTML form.
     *
     * @@Valid --> Tells the Spring MVC to perform validation
     * @BindingResult --> store the results of the validation
     */
    @PostMapping("/processCustomerForm")
    public String processForm(@Valid @ModelAttribute("customer") Customer customer,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors())
            return "customerSignupForm";

        System.out.println(customer);    //logging the data
        return "customerConfirmationForm";
    }

    /**
     * To preprocess the web request at controller level to trim the leading and trailing white spaces.
     */
    @InitBinder
    private void preprocessWebRequest(WebDataBinder webDataBinder) {
        webDataBinder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }
}