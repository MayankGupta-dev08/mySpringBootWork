package dev.mayank.restwebApp.dao;

import dev.mayank.restwebApp.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

/**
 * JpaRepository<EntityType, PrimaryKey>.
 * No need to implement any dao method, since all are predefined and ready to use.
 * Also, @Transactional is not required for the crud methods of this interface.
 */
@RepositoryRestResource(path = "our-employees")
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}