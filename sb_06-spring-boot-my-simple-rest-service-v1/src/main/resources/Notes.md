# Spring Boot: REST Service with JPA/Hibernate

---

## Application Architecture

The Spring Boot REST API project follows a layered architecture, with clear separation of concerns between different components:

         +----------------------+                                 +----------------------+
         |       Controller     |                                 |       Entity         |
         |  (Restful Endpoints) |                                 +----------------------+                
         +----------------------+                              
                       |
                       | HTTP Request/Response
                       |
         +----------------------+
         |       Service        |
         |   (Business Logic)   |
         +----------------------+
                       |
                       | Method Invocation
                       |
         +----------------------+
         |         DAO          |
         | (Data Access Layer)  |
         +----------------------+
                       |
                       | Database Operations
                       |
         +----------------------+
         |       Database       |
         +----------------------+

### Layers of the Architecture:

1. **Controller Layer**:

   - Contains RESTful endpoints responsible for handling HTTP requests and generating HTTP responses.
   - Annotated with `@RestController` or `@Controller` annotations. Also `@RequestMapping` could be used to map web requests to specific handler methods in controllers.
   - Delegates business logic to the service layer and returns data as JSON/XML responses.

2. **Service Layer**:

   - Implements business logic and coordinates interactions between controllers and DAOs.
   - Annotated with `@Service` annotation.
   - Contains service classes with methods performing business operations, applying business rules, and invoking DAO methods.
   - Also, useful in case when we have many different DAOs and for a service, we need to use more than one DAO. 
   - Handles transaction management and data manipulation. So, in the service layer we will have `@Transactional` annotation (whichever method needs it) instead having it in the DOA layer.

3. **DAO Layer**:

   - Responsible for interacting with the database.
   - Annotated with `@Repository` annotation.
   - Contains interfaces or classes defining CRUD operations for database entities.
   - Implements data access logic using Spring Data JPA or JDBC templates.

4. **Database**:
   - Represents the underlying data storage where entities are persisted.
   - Stores data in tables corresponding to entity classes defined in the `entity` package.
   - Entity class is annotated with `@Entity` and `@Table` annotations and fields with `@Column`.

#### Benefits of the Architecture:

- **Separation of Concerns**: Each layer focuses on a specific aspect of the application, promoting modularity and maintainability.
- **Loose Coupling**: Components are loosely coupled, allowing for easier modification, testing, and scalability.
- **Scalability**: Modular structure facilitates the addition or replacement of new features without affecting other components.
- **Testability**: Components can be easily unit tested in isolation, promoting the reliability of the application.
- **Model-View-Controller Pattern**: Follows the **MVC** architectural pattern, where controllers handle HTTP requests, services contain business logic, and DAOs interact with the database.