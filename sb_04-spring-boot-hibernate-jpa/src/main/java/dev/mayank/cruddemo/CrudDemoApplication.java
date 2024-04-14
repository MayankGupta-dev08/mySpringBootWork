package dev.mayank.cruddemo;

import dev.mayank.cruddemo.doa.StudentDAOImpl;
import dev.mayank.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

/**
 * Creating a SpringBoot CLI App (Command Line App)
 */
@SpringBootApplication
class CrudDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(CrudDemoApplication.class, args);
    }

    /**
     * For our CLI App; CommandLineRunner from the SpringBoot Framework will get executed once sspring beans have been loaded.
     * It is also getting the bean for StudentDAOImpl by Dependency Injection.
     */
    @Bean
    @SuppressWarnings("unused")
    public CommandLineRunner commandLineRunner(StudentDAOImpl studentDAO) {
        return runner -> {
            System.out.println("Command Line Started!!");
            performCRUDOperations(studentDAO);
        };
    }

    private void performCRUDOperations(StudentDAOImpl studentDAO) {
        createStudentsAndStoreInDB(studentDAO);
        retrieveStudentEntitiesFromDB(studentDAO);
        updateSomeEntitiesFromDB(studentDAO);
        deleteSomeEntitiesFromDB(studentDAO);
    }

    private void createStudentsAndStoreInDB(StudentDAOImpl studentDAO) {
        List<Student> students = List.of(
                new Student("Leo", "Messi", "leo.messi@arg.com"),
                new Student("Karim", "Benzema", "karim.benzema@frc.com"),
                new Student("Kevin", "De Bruyne", "kevin.debruyne@blg.com"),
                new Student("Christiano", "Ronaldo", "chris.ronaldo@pgl.com"),
                new Student("Erling", "Haaland", "erling.haaland@nrw.com"),
                new Student("Angel", "Di Maria", "angel.dimaria@arg.com")
        );

        System.out.println("Saving students details");
        students.forEach(studentDAO::postEntity);
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private void retrieveStudentEntitiesFromDB(StudentDAOImpl studentDAO) {
        //3. Get a student detail using id
        int id = 1;
        Student response = studentDAO.getEntityById(id);
        if (response != null) System.out.println("Found the detail: " + response.toString());
        else System.out.println("Could not find the student for the id: " + id);
        System.out.println("------------------------------------------------------------------------------------------");

        //4. Get details for all students
        List<Student> studentList = studentDAO.getAllEntities();
        if (!studentList.isEmpty()) {
            System.out.println("List of all the students are:");
            for (Student student : studentList) {
                System.out.println(student.toString());
            }
        } else System.out.println("No entries found in the table!!");
        System.out.println("------------------------------------------------------------------------------------------");

        // 5. Get a student using firstName
        List<Student> result_FN = studentDAO.getEntitiesByQueryingField(
                "firstname", "Kevin", false, "id", true);
        if (result_FN.isEmpty())
            System.out.println("Not Found any entry matching the last_name!!");
        else
            result_FN.forEach(student -> System.out.println("Found: " + student));
        System.out.println("------------------------------------------------------------------------------------------");

        // 6. Get a student using email
        List<Student> ans = studentDAO.getEntitiesByQueryingField(
                "EMAIL", "%arg.com", true, "LASTname", false);
        if (ans.isEmpty())
            System.out.println("Not Found any entry matching the email!!");
        else
            ans.forEach(student -> System.out.println("Found: " + student));
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private void updateSomeEntitiesFromDB(StudentDAOImpl studentDAO) {
        boolean resp = studentDAO.updateFieldOfEntityById(1, "firstName", "Leonel");
        System.out.println("------------------------------------------------------------------------------------------");

        studentDAO.updateFieldOfEntityById(6, "firstname", "Killian");
        System.out.println("------------------------------------------------------------------------------------------");
        studentDAO.updateFieldOfEntityById(6, "lastname", "Mbappe");
        System.out.println("------------------------------------------------------------------------------------------");

        studentDAO.updateAllEntitiesByQuery("email", "abc@gmail.com");
        System.out.println("------------------------------------------------------------------------------------------");
    }

    private void deleteSomeEntitiesFromDB(StudentDAOImpl studentDAO) {
        studentDAO.deleteEntityById(4);
        System.out.println("------------------------------------------------------------------------------------------");
        studentDAO.deleteEntityByQuery("lastname", "Benzema", false);
        System.out.println("------------------------------------------------------------------------------------------");
        studentDAO.deleteAllEntities();
        System.out.println("------------------------------------------------------------------------------------------");
    }

}