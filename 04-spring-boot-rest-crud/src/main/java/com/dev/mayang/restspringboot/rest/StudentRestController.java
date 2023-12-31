package com.dev.mayang.restspringboot.rest;

import com.dev.mayang.restspringboot.entity.Student;
import com.dev.mayang.restspringboot.exception.StudentNotFoundException;
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
    public void loadStudentData() {
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
            throw new StudentNotFoundException("Student not found with id: " + studentId);
        }

        return theStudents.stream().filter(s -> s.getId() == studentId).findFirst().get();
    }
}