package dev.mayank.employee.app.dao;

import dev.mayank.employee.app.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager;

    /**
     * @param roleName
     * @return Role
     */
    @Override
    public Role findRoleByName(String roleName) {
        Role theRole = null;
        try {
            TypedQuery<Role> query = entityManager.createQuery("from Role where name=:rName", Role.class);
            query.setParameter("rName", roleName);
            theRole = query.getSingleResult();
        } catch (Exception ignored) {
            theRole = null;
        }
        return theRole;
    }
}