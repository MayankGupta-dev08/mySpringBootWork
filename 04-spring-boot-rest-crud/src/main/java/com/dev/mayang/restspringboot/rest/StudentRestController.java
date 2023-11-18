package com.dev.mayang.restspringboot.rest;

import com.dev.mayang.restspringboot.entity.Student;
import com.dev.mayang.restspringboot.errorResponse.StudentErrorResponse;
import com.dev.mayang.restspringboot.exception.StudentNotFoundException;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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

    // @ExceptionHandler for handling student exception
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleSNFException(StudentNotFoundException exception) {
        StudentErrorResponse errorResp = new StudentErrorResponse();
        errorResp.setStatus(HttpStatus.NOT_FOUND.value());
        errorResp.setError(HttpStatus.NOT_FOUND.name());
        errorResp.setMessage(exception.getMessage());
        errorResp.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return new ResponseEntity<>(errorResp, HttpStatus.NOT_FOUND);
    }
}