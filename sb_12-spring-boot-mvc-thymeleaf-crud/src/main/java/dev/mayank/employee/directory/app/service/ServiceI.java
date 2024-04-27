package dev.mayank.employee.directory.app.service;

import dev.mayank.employee.directory.app.entity.Employee;

import java.util.List;

public interface ServiceI<T> {

    List<T> findAll();

    T findById(int id);

    Employee save(T obj);

    void deleteById(int id);

}