package dev.mayank.restApp.service;

import dev.mayank.restApp.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

	public User findByUserName(String userName);
}