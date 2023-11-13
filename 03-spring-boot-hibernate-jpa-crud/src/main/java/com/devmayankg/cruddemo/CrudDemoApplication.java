package com.devmayankg.cruddemo;

import com.devmayankg.cruddemo.doa.StudentDAOImpl;
import com.devmayankg.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAO) {
        return runner -> {
            System.out.println("Command Line Started!!");
            processingData(studentDAO);
        };
    }

    private void processingData(StudentDAOImpl studentDAO) {
        //1. Create some student details
        Student student1 = new Student("Leo", "Messi", "leo.messi@arg.com");
        Student student2 = new Student("Karim", "Benzema", "karim.benzema@frc.com");
        Student student3 = new Student("Kevin", "De Bruyne", "kevin.debruyne@blg.com");
        Student student4 = new Student("Christiano", "Ronaldo", "chris.ronaldo@pgl.com");
        Student student5 = new Student("Erling", "Haaland", "erling.haaland@nrw.com");
        Student student6 = new Student("Angel", "Di Maria", "angel.dimaria@arg.com");

        //2. Saving the student details
        studentDAO.postData(student1); studentDAO.postData(student2);
        studentDAO.postData(student3); studentDAO.postData(student4);
        studentDAO.postData(student5); studentDAO.postData(student6);
        System.out.println("Saving students details");
        System.out.println("---------------------------------------------");

        //3. Get a student detail using id
        int id = 1;
        Student response = studentDAO.getDataById(id);
        if (response != null) System.out.println("Found the detail: " + response.toString());
        else System.out.println("Could not find the student for the id: " + id);
        System.out.println("---------------------------------------------");

        //4. Get all students details
        List<Student> studentList = studentDAO.getAllData();
        if (!studentList.isEmpty()) {
            System.out.println("List of all the students are:");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        } else System.out.println("No entries found in the table!!");
        System.out.println("---------------------------------------------");

        // 5. Get a student using firstName
        Optional<Student> result_FN = studentDAO.getDataByFirstName("Kevin").stream().findFirst();
        result_FN.ifPresentOrElse(
                student -> System.out.println("Found: " + student),
                () -> System.out.println("Not Found any entry matching the first_name!!")
        );
        System.out.println("---------------------------------------------");

        // 6. Get a student using lastName
        List<Student> results = studentDAO.getDataByLastName("Messi");
        if (results.isEmpty())
            System.out.println("Not Found any entry matching the last_name!!");
        else
            results.forEach(student -> System.out.println("Found: " + student));
        System.out.println("---------------------------------------------");

        // 6. Get a student using email
        List<Student> ans = studentDAO.getDataByEmail("%arg.com");
        if (ans.isEmpty())
            System.out.println("Not Found any entry matching the email!!");
        else
            ans.forEach(student -> System.out.println("Found: " + student));
        System.out.println("---------------------------------------------");
    }

}