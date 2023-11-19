package com.dev.mayang.restwebapp.dao;

import java.util.List;

public interface DataAccessObject<T> {

    // POST
    String postEntity(T obj);

    // GET
    T getEntityById(int id);

    // GET
    List<T> getAllEntities();

    // DELETE
    String deleteEntityById(T obj);

    // DELETE
    String deleteAllEntities();

    // PUT
    T updateEntity(T obj);
}