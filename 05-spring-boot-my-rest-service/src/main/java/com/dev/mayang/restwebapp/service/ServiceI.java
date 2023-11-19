package com.dev.mayang.restwebapp.service;

import java.util.List;

public interface ServiceI<T> {

    List<T> findAll();

    T findById(int id);

    String save(T obj);

    String deleteById(int id);

    String deleteAll();
}