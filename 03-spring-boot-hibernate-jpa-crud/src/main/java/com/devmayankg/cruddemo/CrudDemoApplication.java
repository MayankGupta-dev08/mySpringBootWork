package com.devmayankg.cruddemo;

import com.devmayankg.cruddemo.doa.StudentDAOImpl;
import com.devmayankg.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAO) {
        return runner -> {
            System.out.println("Command Line Started!!");
            createAndSaveStudent(studentDAO);
        };
    }

    private void createAndSaveStudent(StudentDAOImpl studentDAO) {
        //1. Create some student details
        Student student1 = new Student("Rohit", "Sharma", "hitman.sharma@bcci.com");
        Student student2 = new Student("Virat", "Kohli", "king.kohli@bcci.com");
        System.out.println("created some student details");

        //2. Saving the student details
        studentDAO.postData(student1);
        studentDAO.postData(student2);
    }
}