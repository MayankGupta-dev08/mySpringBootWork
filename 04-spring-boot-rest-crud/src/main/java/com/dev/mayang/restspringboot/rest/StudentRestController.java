package com.dev.mayang.restspringboot.rest;

import com.dev.mayang.restspringboot.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/swagger")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData() {
        this.theStudents = new ArrayList<>();
        theStudents.add(new Student(1, "Walter", "White"));
        theStudents.add(new Student(2, "Jessie", "Pinkman"));
        theStudents.add(new Student(3, "Soul", "Goodman"));
    }

    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        if (studentId <= 0 || studentId > theStudents.size()) {
            // we will throw some exception
            return null;
        }

        return theStudents.stream()
                .filter(s -> s.getId() == studentId)
                .findFirst().get();
    }
}