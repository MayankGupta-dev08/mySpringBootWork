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
                 |                             |           +------------------->-----------------+                                     
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

### After adding `rest.base-path` property and `@RepositoryRestResource`, Endpoints:

- `api` → `our-api`
- `employees` → `our-employee`
- Restriciting Urls -→ Based on roles

1. GET - http://localhost:8080/our-api/our-employees --> Read All ==> CLIENT
2. GET - http://localhost:8080/our-api/our-employees/{employeeId} --> Read single ==> CLIENT
3. POST - http://localhost:8080/our-api/our-employees --> Add single ==> DEVELOPER
4. PUT - http://localhost:8080/our-api/our-employees/{employeeId} --> Update single ==> DEVELOPER
5. DELETE - http://localhost:8080/our-api/our-employees/{employeeId} --> Delete single ==> ADMIN

## CSRF in Security

CSRF, or Cross-Site Request Forgery, is a type of security vulnerability that allows an attacker to trick a user into unintentionally executing actions on a web application in which they are authenticated.

### Here's how it works:

1. **Authentication**: The user is logged into a web application and has an active session.

2. **Malicious Request**: The attacker crafts a malicious request, typically using HTML or JavaScript, and sends it to the user's browser.

3. **User Interaction**: The attacker entices the user to visit a page or click a link that executes the malicious request. This could be done through various social engineering techniques, such as sending a phishing email with a link to the attacker's page.

4. **Unintended Action**: The user's browser, with an active session on the target web application, automatically includes the user's authentication credentials (e.g., session cookie) with the malicious request. Since the request appears to originate from the user, the web application processes it as legitimate.

5. **Attack Execution**: The malicious request is executed by the web application, causing the user's account to perform unintended actions. These actions could range from changing account settings to initiating financial transactions, depending on the permissions of the user's account.

### Preventive Measures for CSRF:

CSRF attacks can have serious consequences, such as unauthorized data modification, financial loss, or even account takeover. To mitigate CSRF attacks, web applications often employ countermeasures such as:

- **CSRF Tokens**: Including a unique token in each form or request that is validated by the server before processing the action. This token is typically generated when the user loads a form or page and must be included with subsequent requests.

- **SameSite Cookies**: Setting the SameSite attribute on cookies to restrict their use to first-party context, preventing them from being sent along with cross-site requests.

- **Anti-CSRF Headers**: Using security headers such as `X-CSRF-Token` or `X-Requested-With` to validate the origin of requests and block those that do not originate from the expected domain.

By implementing these measures, web applications can effectively protect against CSRF attacks and safeguard user accounts and data.