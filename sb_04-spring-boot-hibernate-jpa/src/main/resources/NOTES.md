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