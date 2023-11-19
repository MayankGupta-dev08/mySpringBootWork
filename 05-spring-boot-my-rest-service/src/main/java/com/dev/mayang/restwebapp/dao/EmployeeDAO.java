package com.dev.mayang.restwebapp.dao;

import com.dev.mayang.restwebapp.entity.Employee;

import java.util.List;

public class EmployeeDAO implements DataAccessObject<Employee> {
    @Override
    public void postEntity(Employee obj) {

    }

    @Override
    public Employee getEntityById(int id) {
        return null;
    }

    @Override
    public List<Employee> getAllEntities() {
        return null;
    }

    @Override
    public List<Employee> getEntitiesByQueryingField(String field, String value, boolean isLike, String orderByField, boolean isAsc) {
        return null;
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
}