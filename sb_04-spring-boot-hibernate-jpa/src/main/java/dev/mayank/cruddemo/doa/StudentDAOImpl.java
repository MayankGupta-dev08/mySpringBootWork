package dev.mayank.cruddemo.doa;

import dev.mayank.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

/**
 * All JPQL (Jakarta Persistence API Query Language) is based on Entity Name and Entity fields.
 */
@Repository
public class StudentDAOImpl implements DataAccessObjectI<Student> {

    private EntityManager entityManager;
    private static final Class<Student> MY_CLASS_FOR_TABLE = Student.class;

    @Autowired
    @SuppressWarnings(value = "unused")
    public StudentDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    // POST
    @Override
    @Transactional
    public void postEntity(Student student) {
        entityManager.persist(student);
        System.out.println("Saved the detail of: " + student.toString());
    }


    /**
     * Since Id is a Primary Key we can use find() and it will always return result/null.
     */
    // GET
    @Override
    public Student getEntityById(int id) {
        System.out.println("Finding... the details for the id: " + id);
        return entityManager.find(MY_CLASS_FOR_TABLE, id);
    }

    // GET
    @Override
    public List<Student> getAllEntities() {
        System.out.println("Fetching all the entries from the Student Table....");
        return executeQuery("", "");
    }

    // GET
    @Override
    public List<Student> getEntitiesByQueryingField(String field, String value, boolean isLike, String orderByField, boolean isAsc) {
        field = getExactFieldName(field);
        orderByField = getExactFieldName(orderByField);

        System.out.println(String.format("Fetching entries for all the students whose %s %s %s", field, isLike ? "LIKE" : "=", value));
        // NOTE: for the query entity field names will be exact as the fieldName used in class (firstName and not first_name)
        String whereClause = String.format("WHERE %s %s '%s'", field, isLike ? "LIKE" : "=", value);
        String orderByClause = String.format("ORDER BY %s %s", orderByField, isAsc ? "asc" : "desc");
        return executeQuery(whereClause, orderByClause);
    }

    // PUT
    @Override
    @Transactional
    public boolean updateFieldOfEntityById(int id, String field, String value) {
        if (List.of("firstName", "lastName", "email").stream()
                .anyMatch(f -> f.toLowerCase().equalsIgnoreCase(field))) {
            Student student = getEntityById(id);
            if (student != null) {
                System.out.println("Found the entity with " + id + ": " + student.toString());
                switch (field.toLowerCase()) {
                    case "firstname" -> student.setFirstName(value);
                    case "lastname" -> student.setLastName(value);
                    case "email" -> student.setEmail(value);
                    default -> System.out.println("Invalid field entered!!");
                }
                System.out.println("Updating in progress...");
                entityManager.merge(student);
                System.out.println("Updated student with " + id + ": " + student.toString());
                return true;
            } else {
                System.out.println("Could not find a student with id: " + id);
                return false;
            }
        }
        System.out.println("Error: Incorrect field entered for update method");
        return false;
    }

    // PUT
    @Override
    @Transactional
    public int updateAllEntitiesByQuery(String field, String value) {
        if (Stream.of("firstName", "lastName", "email")
                .anyMatch(f -> f.equalsIgnoreCase(field))) {
            String fieldName = getExactFieldName(field);
            String jpaEntity = MY_CLASS_FOR_TABLE.getSimpleName();
            String ql = String.format("UPDATE %s SET %s = '%s'", jpaEntity, fieldName, value);
            System.out.println("Updating... for query=" + ql + ".");
            int rowsUpdated = entityManager.createNativeQuery(ql, MY_CLASS_FOR_TABLE).executeUpdate();
            System.out.println("Success: Updated rows " + rowsUpdated);
            return rowsUpdated;
        }
        System.out.println("Error: Incorrect field entered for update method");
        return -1;
    }

    // DELETE
    @Override
    @Transactional
    public boolean deleteEntityById(int id) {
        Student student = getEntityById(id);
        if (student != null) {
            System.out.println("Found the entity with " + id + ": " + student.toString());
            System.out.println("Deleting it...");
            entityManager.remove(student);
            System.out.printf("Success: Deleted %s from table.%n", student.toString());
            return true;
        } else {
            System.out.println("Could not find a student with id: " + id);
            return false;
        }
    }

    // DELETE
    @Override
    @Transactional
    public int deleteEntityByQuery(String field, String value, boolean isLike) {
        if (Stream.of("firstName", "lastName", "email")
                .anyMatch(f -> f.equalsIgnoreCase(field))) {
            String fieldName = getExactFieldName(field);
            String jpaEntity = MY_CLASS_FOR_TABLE.getSimpleName();
            String ql = String.format("DELETE FROM %s WHERE %s %s '%s'", jpaEntity, fieldName, isLike ? "LIKE" : "=", value);
            System.out.println("Deleting... for query=" + ql + ".");
            int rowsUpdated = entityManager.createQuery(ql).executeUpdate();
            System.out.println("Success: Deleted rows " + rowsUpdated);
            return rowsUpdated;
        }
        System.out.println("Error: Incorrect field entered for delete operation");
        return -1;
    }

    // DELETE
    @Override
    @Transactional
    public int deleteAllEntities() {
        String jpaEntity = MY_CLASS_FOR_TABLE.getSimpleName();
        String ql = String.format("DELETE FROM %s", jpaEntity);
        System.out.println("Deleting... for query=" + ql + ".");
        int rowsUpdated = entityManager.createQuery(ql).executeUpdate();
        System.out.println("Success: Deleted rows " + rowsUpdated);
        return rowsUpdated;
    }

    private List<Student> executeQuery(String whereClause, String orderByClause) {
        // jpaEntity would not be the name of the db_table but entity className
        String jpaEntity = MY_CLASS_FOR_TABLE.getSimpleName();
        String ql = String.format("FROM %s %s %s", jpaEntity, whereClause, orderByClause);
        // createQuery is used for SELECT queries, so we don't need to mention SELECT again
        TypedQuery<Student> query = entityManager.createQuery(ql, MY_CLASS_FOR_TABLE);
        return query.getResultList();
    }

    private static String getExactFieldName(String field) {
        return Stream.of("firstName", "lastName", "email")
                .filter(f -> f.equalsIgnoreCase(field))
                .findFirst()
                .orElse("null");
    }
}