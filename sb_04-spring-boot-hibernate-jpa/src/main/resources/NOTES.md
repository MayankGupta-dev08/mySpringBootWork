# Spring Boot: Hibernate and JPA Notes

----

## Introduction to JDBC and JPA

### JDBC (Java Database Connectivity)
- **JDBC** is a Java API for connecting and executing SQL queries on a relational database.
- Developers use JDBC to interact directly with the database, writing SQL queries and handling database connections and transactions.
- Key Points:
  - Requires manual handling of SQL queries, database connections, and transactions.
  - Provides a low-level interface for database operations.
  - Not object-oriented; deals with relational data in a procedural manner.

### JPA (Java/Jakarta Persistence API)
- **JPA** is a Java API for Object-Relational Mapping (ORM), providing a high-level abstraction for database interactions.
- Developers work with Java objects instead of SQL queries, allowing for object-oriented database operations.
- Key Points:
  - Standard API for ORM in Java applications (Allows developers to write code which is portable accross different JPA-compliant ORM implementations).
  - Defines a set of interfaces and annotations for working with ORM frameworks.
  - JPA defines entity management features for persisting, retrieving, updating, and deleting Java objects from the database.
  - Encourages a more object-oriented approach to database interactions (JPA includes a query language similar to SQL called JPQL, which allows developers to write database queries using object-oriented syntax).

#### Difference between JDBC and JPA
- **JDBC**:
  - Low-level API for interacting directly with the database.
  - Requires developers to write SQL queries and manage database connections.
  - Procedural approach to database operations.
- **JPA**:
  - High-level API for ORM, abstracting away SQL queries.
  - Allows developers to work with Java objects, providing an object-oriented approach to database interactions.
  - Encourages better code organization and maintainability.

## Understanding ORM and Hibernate

### ORM (Object-Relational Mapping)
- **ORM** is a programming technique that maps object-oriented domain models to relational database models.
- ORM frameworks like Hibernate and implementations of JPA automate the mapping between objects and database tables.
- Key Concepts:
  - **Entity**: Represents a persistent data object stored in a database table.
  - **Mapping**: Defines the correspondence between Java classes and database tables.
  - **Persistence Context**: Manages the lifecycle of entities and database sessions.
  - **Lazy Loading**: Loads related entities from the database only when needed, improving performance.
  - **Dirty Checking**: Detects changes to managed entities and synchronizes them with the database.

### Hibernate
- **Hibernate** is a popular ORM framework for Java, simplifying database interactions by providing high-level abstractions.
- Key Features:
  - **Object-Relational Mapping (ORM)**: Maps Java classes to database tables and data types.
  - **Automatic Persistence**: Manages object persistence, including retrieval, storage, and updates.
  - **Query Language**: Supports Hibernate Query Language (HQL) for writing object-oriented database queries.
  - **Caching**: Implements caching mechanisms to improve application performance.
  - **Transaction Management**: Provides transactional support for ensuring data consistency.

### Additional Notes
- **Hibernate Documentation**: [Hibernate Website](https://www.hibernate.org/orm)
- **JPA Implementations**: Besides Hibernate (most popular and default implementation), other implementations of JPA include EclipseLink, Apache OpenJPA.
- **Benefits of JPA**: Standardizes ORM operations, enabling portability across different ORM implementations and promoting flexible, maintainable code.

## CommandLineRunner in Spring Boot

The `CommandLineRunner` interface in Spring Boot provides a way to execute custom logic or tasks once the Spring application context has been fully initialized. It allows developers to run code at application startup, typically to perform tasks such as initialization, setup, or data loading.

### Interface Definition

The `CommandLineRunner` interface is defined as follows:

```java
@FunctionalInterface
public interface CommandLineRunner {
  void run(String... args) throws Exception;
}
```

- It contains a single method `run(String... args)` that needs to be implemented. This method is invoked by Spring Boot once the application context is ready, and it accepts command-line arguments as parameters.

### Usage

To use CommandLineRunner:

- Define a bean of type CommandLineRunner in your Spring Boot application context.
- Implement the run method of the CommandLineRunner interface to execute the desired tasks or logic.

### Customization

You can create custom implementations of the CommandLineRunner interface to execute specific tasks during application startup. This allows for flexibility and customization according to the requirements of your application.

### Exception Handling

Any exceptions thrown during the execution of the run method will be caught by Spring Boot and logged. You can handle exceptions within the run method if necessary.

### Typical Use Cases

Some typical use cases for CommandLineRunner include:
- Initializing database data or performing database migrations.
- Loading configuration from external sources.
- Performing health checks or system diagnostics.
- Starting background tasks such as schedulers or timers.

### Multiple Runners

- You can define multiple CommandLineRunner beans in your application context. Spring Boot will execute them in the order specified by their @Order annotation or based on the Ordered interface.
- In the provided code example, the CrudDemoApplication class defines a CommandLineRunner bean using the @Bean annotation. The commandLineRunner method implements the run method, where various CRUD operations are performed on the database using the StudentDAOImpl instance.

## JPA Repository vs. JPA EntityManager

### JPA Repository

#### Definition

- **JPA Repository** is a feature provided by Spring Data JPA, which simplifies the implementation of data access layers in Spring applications.
- It provides a set of methods for performing CRUD (Create, Read, Update, Delete) operations on entities without requiring developers to write boilerplate code.
- JPA Repository interfaces are defined by extending `JpaRepository<T, ID>` or other repository interfaces provided by Spring Data JPA.
- Spring Data JPA dynamically generates implementations for these interfaces at runtime.
- Additional features such as pagination and sorting are available.
- Generates queries based on the method names (by following Spring Data JPA's method naming conventions). Can also create custom queries using `@Query`

#### Example

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByLastName(String lastName);
}
```

### JPA EntityManager

#### Definition

- **JPA EntityManager** is an interface provided by the Java Persistence API (JPA) for managing entities in a persistence context.
- It serves as the primary interface for performing CRUD operations, executing queries, and managing the entity lifecycle.
- EntityManager provides methods for persisting, merging, removing, finding, and querying entities, among other operations.
- Developers obtain instances of EntityManager from the EntityManagerFactory.
- Developers use EntityManager to interact directly with the database and perform low-level CRUD operations on entities.
- EntityManager allows fine-grained control over the entity lifecycle, transaction management, and query execution.
- It is preferred when we want to have complex queries that require advanced features such as SQL native queries or stored procedures.
- It supports both JPQL (Java Persistence Query Language) and native SQL queries for querying entities and executing custom SQL statements.

#### Example

```java
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Service
public class StudentService {

    @PersistenceContext
    private EntityManager entityManager;

    public void saveStudent(Student student) {
        entityManager.persist(student);
    }

    public Student findStudentById(Long id) {
        return entityManager.find(Student.class, id);
    }
}
```

### Difference between JPA Repository and JPA EntityManager

- If you need low-level control and flexibility, then use EntityManager.
- If you want a high level of abstraction, then use JpaRepository.

| Aspect                 | JPA Repository                                          | JPA EntityManager                                              |
|------------------------|---------------------------------------------------------|----------------------------------------------------------------|
| Purpose                | Simplifies data access by providing pre-defined methods | Provides low-level API for managing entities and persistence   |
| Abstraction Level      | High-level abstraction over data access operations      | Low-level API for direct manipulation of entities              |
| Code Generation        | Repository interfaces are generated at runtime          | EntityManager instances are obtained from EntityManagerFactory |
| Querying               | Supports method-based query creation and JPQL queries   | Supports JPQL queries and native SQL queries                   |
| Entity Lifecycle       | Limited control over entity lifecycle                   | Fine-grained control over entity lifecycle                     |
| Transaction Management | Managed by Spring Data JPA                              | Must be explicitly managed using `@Transactional`              |
| Typical Use Cases      | Rapid development, basic CRUD operations                | Complex data manipulation, custom queries, batch processing    |