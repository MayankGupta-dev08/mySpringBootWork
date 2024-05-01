package dev.mayank.employee.app.dao;

import dev.mayank.employee.app.entity.Role;

public interface RoleDao {
    Role findRoleByName(String name);
}