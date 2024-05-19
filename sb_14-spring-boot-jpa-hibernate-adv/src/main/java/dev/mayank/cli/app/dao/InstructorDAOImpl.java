package dev.mayank.cli.app.dao;

import dev.mayank.cli.app.entity.Instructor;
import dev.mayank.cli.app.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@AllArgsConstructor
@SuppressWarnings("unused")
public class InstructorDAOImpl implements InstructorDAO {
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Instructor instructor) {
        entityManager.persist(instructor);
    }

    /**
     * This will also retrieve the InstructorDetail object due to the default behavior of @OneToOne fetch-type is Eager.
     */
    @Override
    public Instructor findInstructorById(int id) {
        return entityManager.find(Instructor.class, id);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int id) {
        Instructor instructorById = findInstructorById(id);
        entityManager.remove(instructorById);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int id) {
        return entityManager.find(InstructorDetail.class, id);
    }

    /* @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        entityManager.remove(instructorDetail);
    } */

    /**
     * Removing the associated link b/w the two objects while deleting the instructor detail in order
     * not to support CASCADE.REMOVE and break the bidirectional link for just this operation for instructor_delete
     */
    @Override
    @Transactional
    public void deleteInstructorDetailById(int id) {
        InstructorDetail instructorDetail = findInstructorDetailById(id);
        instructorDetail.getInstructor().setInstructorDetail(null);
        entityManager.remove(instructorDetail);
    }
}