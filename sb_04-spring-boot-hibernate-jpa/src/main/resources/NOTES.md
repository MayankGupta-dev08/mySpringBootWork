# Spring Boot: Hibernate and JPA Notes

----

## Hibernate
- Hibernate is a framework for persisting/saving java objects in a database.
- www.hibernate.org/orm
- Benefits:
  - Hibernate _handles all the low-level SQL_.
  - _Minimizes the amount of JDBC code_ you have to develop.
  - Hibernate provides the **Object-to-Relational Mapping (ORM)**
    - In **ORM** the developer defines the _mapping b/w **Java class** and **Database table**_

## JPA (Jakarta/Java Persistence API)
- **Standard API for ORM**.
- JPA is only a specification and defines a set of interfaces, for which we need to implement for make it usable.
- JPA - Vendor Implementations:
  - **_Hibernate_**
  - EclipseLink
- Benefits of JPA
  - By having a standard API, we are not locked in to a specific vendor's implementation and can theoretically switch vendor if needed.
  - Maintain portable, flexible code by coding to the JPA spec (interfaces).

### Hibernate

Hibernate is a powerful and widely-used Object-Relational Mapping (ORM) framework for Java. It simplifies the process of interacting with relational databases by allowing developers to work with Java objects instead of SQL statements directly. Key features of Hibernate include:

- **Object-Relational Mapping (ORM)**: Hibernate maps Java classes to database tables and Java data types to SQL data types, enabling developers to work with objects rather than database tables and columns.

- **Automatic Persistence**: Hibernate manages the persistence of Java objects to the database, including object retrieval, storage, and updates, using transparent persistence mechanisms.

- **Query Language**: Hibernate Query Language (HQL) allows developers to write database queries using object-oriented syntax, which is then translated into SQL queries by Hibernate.

- **Caching**: Hibernate supports various levels of caching to improve application performance by reducing the number of database queries.

- **Transaction Management**: Hibernate provides transaction management capabilities, allowing developers to define transactional boundaries and ensure data consistency.

### JPA (Java Persistence API)

Java Persistence API (JPA) is a standard Java specification for ORM frameworks. It defines a set of interfaces and annotations that ORM frameworks like Hibernate implement to provide a consistent and standardized way of persisting Java objects in relational databases. Key features of JPA include:

- **Standardization**: JPA provides a standardized way of working with ORM frameworks, allowing developers to write persistence code that is portable across different JPA-compliant ORM implementations.

- **Entity Management**: JPA defines entity management features for persisting, retrieving, updating, and deleting Java objects from the database.

- **JPQL (Java Persistence Query Language)**: JPA includes a query language similar to SQL called JPQL, which allows developers to write database queries using object-oriented syntax.

- **Annotations**: JPA uses annotations to define the mapping between Java classes and database tables, as well as to specify relationships between entities.

### ORM (Object-Relational Mapping)

Object-Relational Mapping (ORM) is a programming technique that allows developers to map object-oriented domain models to relational database models. ORM frameworks like Hibernate and implementations of JPA provide tools and libraries to automate the mapping between objects and database tables, as well as handle database interactions. Key concepts of ORM include:

- **Entity**: An entity represents a persistent data object that is stored in a database table. In Java, entities are typically represented as Java classes.

- **Mapping**: ORM frameworks map Java classes and their attributes to database tables and columns using annotations or XML configuration.

- **Persistence Context**: ORM frameworks maintain a persistence context, which is a collection of managed entities that are associated with a database session.

- **Lazy Loading**: ORM frameworks support lazy loading, where related entities are loaded from the database only when accessed by the application, improving performance by reducing unnecessary database queries.

- **Dirty Checking**: ORM frameworks automatically detect changes to managed entities and synchronize these changes with the database during the transaction commit process.

ORM frameworks like Hibernate and JPA simplify database interaction in Java applications by providing high-level abstractions and reducing the need for boilerplate JDBC code.