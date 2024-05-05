package dev.mayank.employee.app.dao;

import dev.mayank.employee.app.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class UserDaoImpl implements UserDao {

    private EntityManager entityManager;

    /**
     * @param userName
     * @return Role
     */
    @Override
    public User findUserByName(String userName) {
        User theUser = null;
        try {
            TypedQuery<User> query = entityManager.createQuery("from User where username=:uName and enabled=true", User.class);
            query.setParameter("uName", userName);
            theUser = query.getSingleResult();
        } catch (Exception ignored) {
            theUser = null;
        }
        return theUser;
    }
}