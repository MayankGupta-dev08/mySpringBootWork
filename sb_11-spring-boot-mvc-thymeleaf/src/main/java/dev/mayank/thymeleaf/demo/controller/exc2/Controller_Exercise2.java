package dev.mayank.thymeleaf.demo.controller.exc2;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exercise2: Reading Form Data using Spring MVC
 */
@Controller
@RequestMapping("exc2/")
@SuppressWarnings("unused")
public class Controller_Exercise2 {

    /**
     * controller method @<a href="http://localhost:8080/exc2/showForm">showForm</a>
     * to show the initial HTML form.
     */
    @GetMapping("/showForm")
    public String showForm() {
        return "showForm_ex2";
    }

    /**
     * controller method @<a href="http://localhost:8080/exc2/processForm">processForm</a>
     * to process the HTML form.
     */
    @GetMapping("/processForm")
    public String processForm() {
        return "processForm_ex2";
    }
}