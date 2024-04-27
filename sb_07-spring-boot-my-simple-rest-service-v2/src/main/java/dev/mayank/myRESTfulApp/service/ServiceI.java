package dev.mayank.myRESTfulApp.service;

import dev.mayank.myRESTfulApp.entity.Employee;

import java.util.List;

public interface ServiceI<T> {

    List<T> findAll();

    T findById(int id);

    Employee save(T obj);

    void deleteById(int id);

    void deleteAll();

    String updateEntity(T obj);
}