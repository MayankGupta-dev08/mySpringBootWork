package dev.mayank.employee.directory.app.dao;

import dev.mayank.employee.directory.app.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * JpaRepository<EntityType, PrimaryKey>.
 * No need to implement any dao method, since all are predefined and ready to use.
 * Also, @Transactional is not required for the crud methods of this interface.
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * method to sort by last name
     */
    List<Employee> findAllByOrderByLastNameAsc();
}