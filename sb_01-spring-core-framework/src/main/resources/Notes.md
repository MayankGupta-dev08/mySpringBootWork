# Spring Framework

- Spring is a Dependency Injection Framework for java to make java applications loosely coupled.
- Spring framework makes the development of JavaEE applications easy.
- It was developed in 2003 by Rod Johnson.
- Two main concepts: IoC (Inversion of Control) and DI (Dependency Injection).

---

## Basic Flow of Spring and JEE application

         +----------------------+                 
         |       UI/UX Layer    |                 
         +----------------------+                              
                  |   ^                 Spring MVC
                  |   |  HTTP Request/Response
                  v   | 
         +----------------------+
         |   Service/Business   |
         |   (Business Logic)   |
         +----------------------+
                  |   ^                 Spring Security
                  |   |  Method Invocation
                  v   | 
         +----------------------+
         |  Data Access Layer   |
         |    (Persistance)     |
         +----------------------+
                  |   ^                 Spring JDBC
                  |   |  Database Operations
                  v   |                 Spring ORM/Hibernate
         +----------------------+
         |       Database       |
         +----------------------+

## Modules in Spring Framework

### 1. Spring Core

- Core
- Beans
- Context
- spEL (Spring Expression Language)

### 2. Spring AOP

- AOP
- Aspect
- Instrumentation
- Messaging

### 3. Data Access/Integration

- JDBC (Java Database Connectivity)
- ORM (Object Relational Mapping)
- OXM (Object XML Mapping)
- JMS (Java Messaging Service)

### 4. Spring Web

- Web
- Servlet
- Portlet
- WebSocket

### 5. Spring Test

## Key Concepts

### 1. Spring IoC container

- Beans (Java POJO)
- Config5
- Container
- Bean Lifecycle Management (Creation, Dependency Injection, and Deletion of Beans)

### 2. ApplicationContext (extends from BeanFactory)

- ClasspathXMLApplicationContext
- AnnotationConfigApplicationContext
- FileSystemXMLApplicationContext 