package dev.mayank.thymeleaf.demo.controller.exc3;

import dev.mayank.thymeleaf.demo.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Exercise3: Reading Form Data using Spring MVC
 *
 * @Value("${propertName}") is used to import the values of the field from `application.properties`
 */
@Controller
@RequestMapping("students/")
@SuppressWarnings("unused")
public class StudentController {

    @Value("${countries}")
    private List<String> countries;

    @Value("${languages}")
    private List<String> languages;

    @Value("${systems}")
    private List<String> systems;

    /**
     * controller method @<a href="http://localhost:8080/students/showStudentForm">showStudentForm</a>
     * to show the initial HTML form.
     */
    @GetMapping("/showStudentForm")
    public String showForm(Model model) {
        model.addAttribute("student", new Student());

        // we need to add this data to the Model since these are possible values from which user will select
        model.addAttribute("countries", countries);
        model.addAttribute("languages", languages);
        model.addAttribute("systems", systems);
        return "showStudentForm";
    }

    /**
     * controller method @<a href="http://localhost:8080/students/processStudentForm">processStudentForm</a>
     * to process the HTML form.
     */
    @PostMapping("/processStudentForm")
    public String processForm(@ModelAttribute("student") Student student) {
        System.out.println(student);    //logging the data
        return "processStudentForm";
    }
}