package dev.mayank.employee.app.service;

import dev.mayank.employee.app.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
	public User findUserByName(String userName);
}