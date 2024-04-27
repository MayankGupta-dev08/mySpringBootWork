package dev.mayank.thymeleaf.demo.controller.exc2;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    /**
     * controller method @<a href="http://localhost:8080/exc2/processFormV2">processFormV2</a>
     * to process the HTML form.
     */
    @GetMapping("/processFormV2")
    public String processFormV2(HttpServletRequest httpServletRequest, Model model) {
        String studentName = httpServletRequest.getParameter("studentName");
        String msg = "Hey there, Welcome %s.".formatted(studentName.toUpperCase());
        model.addAttribute("message", msg);
        return "processFormV2_ex2";
    }

    /**
     * controller method @<a href="http://localhost:8080/exc2/processFormV3">processFormV3</a>
     * to process the HTML form.
     */
    @GetMapping("/processFormV3")
    public String processFormV3(@RequestParam("studentName") String theStudentName, Model model) {
        String msg = "Hola, bienvenido %s.".formatted(theStudentName.toUpperCase());
        model.addAttribute("message", msg);
        return "processFormV2_ex2";
    }
}