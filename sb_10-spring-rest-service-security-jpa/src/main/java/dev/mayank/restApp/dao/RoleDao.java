package dev.mayank.restApp.dao;


import dev.mayank.restApp.entity.Role;

public interface RoleDao {
	public Role findRoleByName(String theRoleName);
}