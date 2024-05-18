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
        return runner -> createInstructors(instructorDAO);
    }

    private void createInstructors(InstructorDAO instructorDAO) {
        InstructorDetail instructorDetail1 = new InstructorDetail("codeWithHarry", "python");
        Instructor instructor1 = new Instructor("Harry", "Bhai", "harisKhan@iitkgp.edu.in");

        InstructorDetail instructorDetail2 = new InstructorDetail("luv2Code", "java");
        Instructor instructor2 = new Instructor("Chad", "Darby", "chadDarby@cmu.edu.us");
    }
}