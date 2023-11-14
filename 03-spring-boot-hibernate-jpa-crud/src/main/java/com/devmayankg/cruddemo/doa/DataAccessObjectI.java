package com.devmayankg.cruddemo.doa;

import java.util.List;

public interface DataAccessObjectI<T> {

    // POST
    void postEntity(T obj);


    // GET
    T getEntityById(int id);

    // GET
    List<T> getAllEntities();

    // GET
    List<T> getEntitiesByFirstName(String firstName);

    // GET
    List<T> getEntitiesByEmail(String email);


    // PUT
    boolean updateFirstNameById(int id, String firstName);

    // PUT
    boolean updateFieldOfEntityById(int id, String field, String value);

    // PUT
    int updateByQuery(String field, String value);


    // DELETE
    void deleteEntityById();

    // DELETE
    void deleteAllEntities();
}