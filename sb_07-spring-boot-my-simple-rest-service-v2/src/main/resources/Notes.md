# SpringBoot: RESTful Service with JPA

---

## Difference b/w this module (sb07) and the previous module (sb06)

- The main difference is the implementation of DAO Layer.
- In previous module we had used the EntityManager Interface of JPA with Hibernate implementation.
- The EntityManager is directly injected in the DAO class and used to interact directly with the database and perform low-level CRUD operations on entities.
- In this module we have used the JpaRepository Interface to simplifies the implementation of data access layer and use the default implementation for the CRUD operation provided by the Spring Framework (Spring Data JPA).
- [For more info on EntityManager and JpaRepository...](sb_04-spring-boot-hibernate-jpa/src/main/resources/NOTES.md)