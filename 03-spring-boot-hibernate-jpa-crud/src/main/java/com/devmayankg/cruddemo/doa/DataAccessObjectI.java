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
    List<T> getEntitiesByQueryingField(String field, String value, boolean isLike, String orderByField, boolean isAsc);


    // PUT
    boolean updateFieldOfEntityById(int id, String field, String value);

    // PUT
    int updateAllEntitiesByQuery(String field, String value);


    // DELETE
    boolean deleteEntityById(int id);

    // DELETE
    int deleteEntityByQuery(String field, String value, boolean isLikeOperator);

    // DELETE
    int deleteAllEntities();
}