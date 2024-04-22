package dev.mayank.restApp.dao;

import dev.mayank.restApp.entity.User;

public interface UserDao {

    User findByUserName(String userName);
}