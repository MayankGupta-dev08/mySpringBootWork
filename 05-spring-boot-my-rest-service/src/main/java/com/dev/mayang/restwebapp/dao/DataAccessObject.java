package com.dev.mayang.restwebapp.dao;

import java.util.List;

public interface DataAccessObject<T> {

    // POST
    String postEntity(T obj);


    // GET
    T getEntityById(int id);

    // GET
    List<T> getAllEntities();

    // GET
    List<T> getEntitiesByQueryingField(String field, String value, boolean isLike, String orderByField, boolean isAsc);


    // PUT
    boolean updateFieldOfEntityById(int id, String field, String value);

    // PUT
    int updateAllEntitiesByQuery(String field, String value);


    // DELETE
    String deleteEntityById(T obj);

    // DELETE
    int deleteEntityByQuery(String field, String value, boolean isLikeOperator);

    // DELETE
    String deleteAllEntities();
}