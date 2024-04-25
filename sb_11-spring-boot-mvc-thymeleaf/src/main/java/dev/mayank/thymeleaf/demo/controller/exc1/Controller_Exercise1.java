package dev.mayank.thymeleaf.demo.controller.exc1;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Exercise1: Getting Started
 */
@Controller
@SuppressWarnings("unused")
@RequestMapping("exc1/")
public class Controller_Exercise1 {

    /**
     * myMsg.html will be looked for under resources/templates/ as the response for this request.
     * <p>Thymeleaf will use the Spring MVC Model to access the Date using theDate attribute.</p>
     */
    @GetMapping("/hello")
    public String sayHello(Model model) {
        model.addAttribute("theDate", new Date());
        return "myMsg";
    }
}