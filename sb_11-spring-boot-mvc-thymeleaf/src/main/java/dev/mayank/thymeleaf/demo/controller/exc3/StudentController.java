package dev.mayank.thymeleaf.demo.controller.exc3;

import dev.mayank.thymeleaf.demo.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Exercise3: Reading Form Data using Spring MVC
 */
@Controller
@RequestMapping("students/")
@SuppressWarnings("unused")
public class StudentController {

    /**
     * controller method @<a href="http://localhost:8080/exc3/showStudentForm">showStudentForm</a>
     * to show the initial HTML form.
     */
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());
        return "showStudentForm";
    }

    /**
     * controller method @<a href="http://localhost:8080/exc3/processStudentForm">processStudentForm</a>
     * to process the HTML form.
     */
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {
        System.out.println(student);    //logging the data
        return "processStudentForm";
    }
}