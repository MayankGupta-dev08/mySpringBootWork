package dev.mayank.restApp.rest;

import dev.mayank.restApp.entity.Student;
import dev.mayank.restApp.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@SuppressWarnings("unused")
@RequestMapping("/swagger") // http://localhost:8080/swagger/
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