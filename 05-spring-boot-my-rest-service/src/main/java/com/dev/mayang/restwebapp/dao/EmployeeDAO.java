package com.dev.mayang.restwebapp.dao;

import com.dev.mayang.restwebapp.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Stream;

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
    public void postEntity(Employee obj) {

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
    public List<Employee> getEntitiesByQueryingField(String field, String value, boolean isLike, String orderByField, boolean isAsc) {
        // NOTE: for the query entity field names will be exact as the fieldName used in class (firstName and not first_name)
        field = getExactFieldName(field);
        orderByField = getExactFieldName(orderByField);
        System.out.println(String.format("Fetching entries for all the students whose %s %s %s", field, isLike ? "LIKE" : "=", value));

        String whereClause = String.format("WHERE %s %s '%s'", field, isLike ? "LIKE" : "=", value);
        String orderByClause = String.format("ORDER BY %s %s", orderByField, isAsc ? "asc" : "desc");
        return executeQuery(whereClause, orderByClause);
    }

    @Override
    public boolean updateFieldOfEntityById(int id, String field, String value) {
        return false;
    }

    @Override
    public int updateAllEntitiesByQuery(String field, String value) {
        return 0;
    }

    @Override
    public boolean deleteEntityById(int id) {
        return false;
    }

    @Override
    public int deleteEntityByQuery(String field, String value, boolean isLikeOperator) {
        return 0;
    }

    @Override
    public int deleteAllEntities() {
        return 0;
    }

    private List<Employee> executeQuery(String whereClause, String orderByClause) {
        // jpaEntity would not be the name of the db_table but entity className
        String jpaEntity = TABLE_CLASS.getSimpleName();
        String ql = String.format("FROM %s %s %s", jpaEntity, whereClause, orderByClause);

        // createQuery is used for SELECT queries, so we don't need to mention SELECT again
        TypedQuery<Employee> query = entityManager.createQuery(ql, TABLE_CLASS);
        return query.getResultList();
    }

    private static String getExactFieldName(String field) {
        return Stream.of("firstName", "lastName", "email")
                .filter(f -> f.equalsIgnoreCase(field))
                .findFirst()
                .orElse("null");
    }
}