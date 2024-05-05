package dev.mayank.employee.app.dao;

import dev.mayank.employee.app.entity.User;

public interface UserDao {
    User findUserByName(String userName);
}