package com.devmayankg.cruddemo.doa;

import java.util.List;

public interface DataAccessObjectI<T> {

    // POST
    void postData(T obj);

    // GET
    T getDataById(int id);
    List<T> getDataByFirstName(String firstName);
    List<T> getDataByLastName(String lastName);
    List<T> getAllData();

    // PUT
    boolean updateById(int id);

    // DELETE
    void deleteById();
    void deleteAll();
}