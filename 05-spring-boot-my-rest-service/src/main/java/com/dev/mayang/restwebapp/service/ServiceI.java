package com.dev.mayang.restwebapp.service;

import java.util.List;

public interface ServiceI<T> {

    List<T> findAll();

    T findById(int id);

    void save(T obj);
}