package com.devmayankg.cruddemo.doa;

import com.devmayankg.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        return null;
    }

    // GET
    @Override
    public List<Student> getDataByFirstName(String firstName) {
        return null;
    }

    // GET
    @Override
    public List<Student> getDataByLastName(String lastName) {
        return null;
    }

    // GET
    @Override
    public List<Student> getAllData() {
        return null;
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
}