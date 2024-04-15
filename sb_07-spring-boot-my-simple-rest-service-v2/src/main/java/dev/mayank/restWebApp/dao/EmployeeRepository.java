package dev.mayank.restWebApp.dao;

import dev.mayank.restWebApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * JpaRepository<EntityType, PrimaryKey>.
 * No need to implement any dao method, since all are predefined and ready to use.
 * Also, @Transactional is not required for the crud methods of this interface.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}