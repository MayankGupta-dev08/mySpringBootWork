package com.devmayankg.cruddemo.doa;

import com.devmayankg.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * All JPQL (Jakarta Persistence API Query Language) is based on Entity Name and Entity fields.
 */
@Repository
public class StudentDAOImpl implements DataAccessObjectI<Student> {

    private EntityManager entityManager;

    @Autowired
    @SuppressWarnings(value = "unused")
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // POST
    @Override
    @Transactional
    public void postEntity(Student student) {
        entityManager.persist(student);
        System.out.println("Saved the detail of: " + student.toString());
    }

    // GET

    /**
     * Since Id is a Primary Key we can use find() and it will always return result/null.
     */
    @Override
    public Student getEntityById(int id) {
        System.out.println("Finding... the details for the id: " + id);
        return entityManager.find(Student.class, id);
    }

    // GET
    @Override
    public List<Student> getAllEntities() {
        System.out.println("Fetching all the entries from the Student Table....");
        return executeQuery("", "");
    }

    // GET
    @Override
    public List<Student> getEntitiesByFirstName(String firstName) {
        System.out.println("Fetching entries for all the students whose firstName=" + firstName);
        // NOTE: for the query entity field names will be exact as the fieldName used in class (firstName and not first_name)
        return executeQuery(" WHERE firstName='" + firstName + "'", " ORDER BY firstName asc");
    }

    // GET
    public List<Student> getEntitiesByEmail(String email) {
        System.out.println("Fetching entries for all the students whose email is LIKE '" + email + "'");
        return executeQuery(" WHERE email LIKE '" + email + "'", " ORDER BY id desc");
    }

    // PUT
    @Override
    @Transactional
    public boolean updateFirstNameById(int id, String firstName) {
        Student student = getEntityById(id);
        if (student != null) {
            student.setFirstName(firstName);
            entityManager.merge(student);
            System.out.println("Updated student with " + id + ": " + student.toString());
            return true;
        }
        System.out.println("Could not find a student with id: " + id);
        return false;
    }

    // PUT
    @Override
    @Transactional
    public boolean updateFieldOfEntityById(int id, String field, String value) {
        if (List.of("firstName", "lastName", "email").stream()
                .anyMatch(f -> f.toLowerCase().equalsIgnoreCase(field))) {
            Student student = getEntityById(id);
            if (student != null) {
                System.out.println("Found the entity with " + id + ": " + student.toString());
                switch (field.toLowerCase()) {
                    case "firstname" -> student.setFirstName(value);
                    case "lastname" -> student.setLastName(value);
                    case "email" -> student.setEmail(value);
                    default -> System.out.println("Invalid field entered!!");
                }
                System.out.println("Updating in progress...");
                entityManager.merge(student);
                System.out.println("Updated student with " + id + ": " + student.toString());
                return true;
            } else {
                System.out.println("Could not find a student with id: " + id);
                return false;
            }
        }
        System.out.println("Error: Incorrect field entered for update method");
        return false;
    }

    // PUT
    @Override
    @Transactional
    public int updateAllEntitiesByQuery(String field, String value) {
        if (List.of("firstName", "lastName", "email").stream()
                .anyMatch(f -> f.toLowerCase().equalsIgnoreCase(field))) {
            Class<Student> myClass = Student.class;
            String jpaEntity = myClass.getSimpleName();
            String ql = "UPDATE " + jpaEntity + " SET " + field + "='" + value + "'";
            System.out.println("Updating... for query=" + ql + ".");
            int rowsUpdated = entityManager.createNativeQuery(ql, myClass).executeUpdate();
            System.out.println("Success: Updated rows " + rowsUpdated);
            return rowsUpdated;
        }
        System.out.println("Error: Incorrect field entered for update method");
        return -1;
    }

    // DELETE
    @Override
    public void deleteEntityById() {

    }

    // DELETE
    @Override
    public void deleteAllEntities() {

    }

    private List<Student> executeQuery(String whereClause, String orderByClause) {
        // jpaEntity would not be the name of the db_table but entity className
        Class<Student> myClass = Student.class;
        String jpaEntity = myClass.getSimpleName();
        String ql = "FROM " + jpaEntity + whereClause + orderByClause;
        TypedQuery<Student> query = entityManager.createQuery(ql, myClass);
        return query.getResultList();
    }
}