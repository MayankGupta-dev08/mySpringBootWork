package dev.mayank.restApp.dao;

import dev.mayank.restApp.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class RoleDaoImpl implements RoleDao {

    private EntityManager entityManager;

    @Override
    public Role findRoleByName(String theRoleName) {
        Role theRole = null;
        try {
            TypedQuery<Role> theQuery = entityManager.createQuery("from Role where name=:roleName", Role.class);
            theQuery.setParameter("roleName", theRoleName);
            theRole = theQuery.getSingleResult();
        } catch (Exception ignored) {
            theRole = null;
        }
        return theRole;
    }
}