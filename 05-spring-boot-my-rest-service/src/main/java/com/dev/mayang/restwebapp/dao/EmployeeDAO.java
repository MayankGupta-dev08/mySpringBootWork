package com.dev.mayang.restwebapp.dao;

import com.dev.mayang.restwebapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Basic Roadmap for all methods: 1. Create Query | 2. Execute the query to get the result | 3. Return the result.
 */

@Repository
public class EmployeeDAO implements DataAccessObject<Employee> {

    // field for EntityManager
    private EntityManager entityManager;
    private static final Class<Employee> TABLE_CLASS = Employee.class;

    @Autowired  // Constructor Injection for EntityManager
    @SuppressWarnings("unused")
    public EmployeeDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public String postEntity(Employee employee) {
        System.out.println("Saving employee...");
        entityManager.persist(employee);
        return String.format("Saved Employee: %s", employee.toString());
    }

    @Override
    public Employee getEntityById(int id) {
        System.out.println(String.format("Fetching the details of employee with id:%s: ...", id));
        return entityManager.find(TABLE_CLASS, id);
    }

    @Override
    public List<Employee> getAllEntities() {
        System.out.println("Fetching all the entries from the Student Table....");
        return executeQuery("", "");
    }

    @Override
    public String deleteEntityById(Employee employee) {
        entityManager.remove(employee);
        return String.format("Deleted Employee: %s", employee.toString());
    }

    @Override
    public String deleteAllEntities() {
        String jpaEntity = TABLE_CLASS.getSimpleName();
        String ql = String.format("DELETE FROM %s", jpaEntity);
        System.out.println("Deleting all the data ...");
        int rowsUpdated = entityManager.createQuery(ql).executeUpdate();
        return String.format("Success!! Deleted rows %s.", rowsUpdated);
    }

    @Override
    public Employee updateEntity(Employee employee) {
        System.out.println("Updating ...");
        return entityManager.merge(employee);
    }

    private List<Employee> executeQuery(String whereClause, String orderByClause) {
        // jpaEntity would not be the name of the db_table but entity className
        String jpaEntity = TABLE_CLASS.getSimpleName();
        String ql = String.format("FROM %s %s %s", jpaEntity, whereClause, orderByClause);

        // createQuery is used for SELECT queries, so we don't need to mention SELECT again
        TypedQuery<Employee> query = entityManager.createQuery(ql, TABLE_CLASS);
        return query.getResultList();
    }
}