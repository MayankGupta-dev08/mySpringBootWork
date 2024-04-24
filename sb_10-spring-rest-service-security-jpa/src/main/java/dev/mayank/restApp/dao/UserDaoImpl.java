package dev.mayank.restApp.dao;

import dev.mayank.restApp.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    @Override
    public User findByUserName(String theUserName) {
        User theUser = null;
        try {
            TypedQuery<User> theQuery = entityManager.createQuery("from User where userName=:uName and enabled=true", User.class);
            theQuery.setParameter("uName", theUserName);
            theUser = theQuery.getSingleResult();
        } catch (Exception ignored) {
            theUser = null;
        }
        return theUser;
    }
}