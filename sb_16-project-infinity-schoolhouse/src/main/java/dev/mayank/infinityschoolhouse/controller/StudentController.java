package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Person;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("student")
@SuppressWarnings("unused")
public class StudentController {

    @RequestMapping("/displayStudentCourses")
    public ModelAndView displayStudentCourses(Model mode, HttpSession session) {
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        log.info("Displaying courses for student: {}", loggedInPerson.getName());
        ModelAndView enrolledCoursesMV = new ModelAndView("student_courses.html");
        enrolledCoursesMV.addObject("person", loggedInPerson);
        return enrolledCoursesMV;
    }
}