# SpringBoot: RESTFul Service with REST Security

- Define Users and Roles, and protect URLs based on roles.
- Store users, passwords, and roles in the DB in plain/encrypted form.

---

## Spring Security Model

- Spring Security defines a framework for security.
- Implemented using servlet filters in the background.
- Two methods of securing an app: declarative and programmatic.
- Servlet Filters are used to pre-process/post-process web requests.
- Servlet Filters can route web requests based on security logic.
- Spring provides a bulk of security functionality with servlet filters.

### Servlets

- Servlets are small Java programs that run within a web server.
- They handle requests from web clients, process them, and return a response to the client.
- Servlets are the backbone of Java web applications and are widely used for creating dynamic web content.

### Spring Security Overview

Spring Security handles authentication, authorization, and protection against common security threats like session fixation, clickjacking, cross-site request forgery (CSRF), and cross-site scripting (XSS).


                 +-----------------------------+                                                
                 |                             |           +------------------->-----------------|                                     
                 |         Web Request         |           |                                     | 
                 |                             |           |                                     v
                 +-------------+---------------+           |                      +--------------+-----------------+                   
                               |                           |                      |                                |
                               v                           |                      |         Authentication         |
                 +-------------+---------------+           |                      |                                |               
                 |                             |           |                      +--------------+-----------------+                                       
                 |   Spring Security Filter    |           |                                     |
                 |                             |           |                                     v
                 +-------------+---------------+           |                      +--------------+-----------------+               
                               |                           |                      |                                |
                               v                           |                      |          Authorization         |
                 +-------------+---------------+           |                      |                                |                
                 |                             |           |                      +--------------+-----------------+               
                 |   Authentication Manager    |           |                                     |                                     
                 |                             |           |                                     v                                     
                 +-------------+---------------+           |                      +--------------+-----------------+                                     
                               |                           |                      |                                |                     
                               v                           |                      |         Access Decision        |                     
                 +-------------+---------------+           |                      |                                |                                     
                 |                             |           |                      +--------------+-----------------+                                     
                 |        User Details         |           |                                     |                                     
                 |   (UserDetailsService)      |           |                                     v                                     
                 |                             |           |                      +--------------+-----------------+                                     
                 +-------------+---------------+           |                      |                                |                                     
                               |                           |                      |          Web Response          |                     
                               v                           |                      |                                |                     
                 +-------------+---------------+           |                      +--------------------------------+                                     
                 |                             |           |                                                                          
                 |     Password Encoder        |           |                                                                          
                 |                             |           |                                                                          
                 +-------------+---------------+           |
                               |                           |
                               v-------------------------->+

## Spring Security Concepts

1. Authentication
    - Verify the user with the ID and password; if existing in the DB, then proceed; otherwise, reject.
2. Authorization
    - Validate if the user is authorized to perform the particular operation using their role.

### Declarative Security

- Define application’s security constraints in configuration.
    - All Java config: `@Configuration`.
- Provides a separation of concerns between application code and security.

### Programmatic Security

- Spring Security provides an API for custom application coding.
- Provides greater customization for specific app requirements.

### Enabling Spring Security

- Edit `pom.xml` and add `spring-boot-starter-security`; this will automatically secure your endpoints.
- You can override the default username and generate a password using the `application.properties`.
    - `spring.security.user.name`
    - `spring.security.user.password`
- Authentication and Authorization could be done in different ways:
    - In-memory
    - JDBC
    - LDAP
    - Custom/Pluggable

## After adding `rest.base-path` property and `@RepositoryRestResource`, Custom REST ENDPOINTS

- `api` → `our-api`
- `employees` → `our-employee`

## RESTRICTING URLS → BASED ON ROLES

1. GET - http://localhost:8080/our-api/our-employees --> Read All ==> CLIENT
2. GET - http://localhost:8080/our-api/our-employees/{employeeId} --> Read single ==> CLIENT
3. POST - http://localhost:8080/our-api/our-employees --> Add single ==> DEVELOPER
4. PUT - http://localhost:8080/our-api/our-employees/{employeeId} --> Update single ==> DEVELOPER
5. DELETE - http://localhost:8080/our-api/our-employees/{employeeId} --> Delete single ==> ADMIN