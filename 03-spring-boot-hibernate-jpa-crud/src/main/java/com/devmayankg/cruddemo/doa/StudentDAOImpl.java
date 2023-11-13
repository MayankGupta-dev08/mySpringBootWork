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
    public void postData(Student student) {
        entityManager.persist(student);
        System.out.println("Saved the detail of: " + student.toString());
    }

    // GET
    @Override
    public Student getDataById(int id) {
        System.out.println("Finding... the details for the id: " + id);
        return entityManager.find(Student.class, id);
    }

    // GET
    @Override
    public List<Student> getDataByFirstName(String firstName) {
        System.out.println("Fetching entries for all the students whose firstName=" + firstName);
        // NOTE: for the query entity field names will be exact as the fieldName used in class (firstName and not first_name)
        return executeQuery(" WHERE firstName='" + firstName + "'");
    }

    // GET
    @Override
    public List<Student> getDataByLastName(String lastName) {
        System.out.println("Fetching entries for all the students whose lastName=" + lastName);
        // NOTE: for the query entity field names will be exact as the fieldName used in class (lastName and not last_name)
        return executeQuery(" WHERE lastName='" + lastName + "'");
    }

    // GET
    @Override
    public List<Student> getAllData() {
        System.out.println("Fetching entries for all the students....");
        return executeQuery("");
    }

    // PUT
    @Override
    public boolean updateById(int id) {
        return false;
    }

    // DELETE
    @Override
    public void deleteById() {

    }

    // DELETE
    @Override
    public void deleteAll() {

    }

    private List<Student> executeQuery(String whereClause) {
        // jpaEntity would not be the name of the db_table but entity className
        Class<Student> myClass = Student.class;
        String jpaEntity = myClass.getSimpleName();
        String ql = "FROM " + jpaEntity + whereClause;
        TypedQuery<Student> query = entityManager.createQuery(ql, myClass);
        return query.getResultList();
    }
}