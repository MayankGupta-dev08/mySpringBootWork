package dev.mayank.cli.app;

import dev.mayank.cli.app.dao.InstructorDAO;
import dev.mayank.cli.app.entity.Instructor;
import dev.mayank.cli.app.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@SuppressWarnings("unused")
public class MyCLIApp {

    public static void main(String[] args) {
        SpringApplication.run(MyCLIApp.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(InstructorDAO instructorDAO) {
        return runner -> {
            createInstructors(instructorDAO);
            System.out.println("Instructors created!");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            findTheInstructorById(instructorDAO);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            deleteTheInstructorById(instructorDAO);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            findTheInstructorDetailById(instructorDAO);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

            deleteTheInstructorDetailById(instructorDAO);
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        };
    }

    private void createInstructors(InstructorDAO instructorDAO) {
        InstructorDetail instructorDetail1 = new InstructorDetail("codeWithHarry", "python");
        Instructor instructor1 = new Instructor("Harry", "Bhai", "harisKhan@iitkgp.edu.in");
        instructor1.setInstructorDetail(instructorDetail1);
        System.out.println("Saving... " + instructor1);
        instructorDAO.save(instructor1);

        InstructorDetail instructorDetail2 = new InstructorDetail("luv2Code", "java");
        Instructor instructor2 = new Instructor("Chad", "Darby", "chadDarby@cmu.edu.us");
        instructor2.setInstructorDetail(instructorDetail2);
        System.out.println("Saving... " + instructor2);
        instructorDAO.save(instructor2);
    }

    private void findTheInstructorById(InstructorDAO instructorDAO) {
        int id = 1;
        System.out.println("finding the instructor for id = " + id);
        Instructor instructorById = instructorDAO.findInstructorById(id);
        System.out.println("Result: " + instructorById);
    }

    private void deleteTheInstructorById(InstructorDAO instructorDAO) {
        int id = 1;
        System.out.println("deleting the instructor for id = " + id);
        instructorDAO.deleteInstructorById(id);
        System.out.println("deleted instructor for id = " + id);
    }

    private void findTheInstructorDetailById(InstructorDAO instructorDAO) {
        int id = 2;
        System.out.println("finding the instructor detail for id = " + id);
        InstructorDetail instructorDetail = instructorDAO.findInstructorDetailById(id);
        System.out.println("Result: " + instructorDetail);
    }

    private void deleteTheInstructorDetailById(InstructorDAO instructorDAO) {
        int id = 2;
        System.out.println("deleting the instructor detail for id = " + id);
        instructorDAO.deleteInstructorDetailById(id);
        System.out.println("deleted instructor for id = " + id);
    }
}