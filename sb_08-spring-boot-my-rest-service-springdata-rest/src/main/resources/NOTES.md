# SpringBoot: RESTful App using SPRING DATA REST

---

## Classes needed

1. Our Entity Class: Employee
2. Our DAO Interface: EmployeeRepository extends JpaRepository<EntityClass, PrimaryKey>
3. Our SpringBootApplication Class: MyRESTfulWebApp

- Notes:
    1. Need additional dependency in maven pom: spring-boot-starter-data-rest
    2. No need of (RestController Class or Service Class) due of spring-boot-starter-data-rest and neither of (DAO
       Class) due to JpaRepository.

## Workflow

    ** No Need of Controller or Service Layer **

         +----------------------+                                 +----------------------+
         |      RESTFul App     |                                 |       Entity         |
         |   (SpringBoot App)   |                                 +----------------------+                
         +----------------------+                              
                     |   
                     |    HTTP Request/Response
                     |    (Automatically Handled by SpringDataRest)
                     |    Method Invocation
                     |   
         +-----------------------+
         |         DAO           |
         | (Using JpaRepository) |
         +-----------------------+
                     |   
                     |    Database Operations
                     |   
         +-----------------------+
         |       Database        |
         +-----------------------+

## API Endpoint variations in the project

- Spring Data REST endpoints are HATEOAS (Hypermedia As The Engine Of Application State) compliant.
- Hypermedia-drive sites provide information to access REST interfaces (meta-data for REST).
- Response will include additional meta-data, such as links and page related info.
- For example, for a REST response from GET: http://localhost:8080/employees/3
```json
{
  "firstName": "Anvi",
  "lastName": "Gupta",
  "email": "anvi.gupta@codeWithMayank.com",
  "_links": {
    "self": {
      "href": "http://localhost:8080/employees/3"
    },
    "employee": {
      "href": "http://localhost:8080/employees/3"
    }
  }
}
```
- For a response which is collection, it includes page size, total elements, pages etc.
- For example, for a REST response from GET: http://localhost:8080/employees/
```json
{
  "_embedded": {
    "employees": [
      {
        "firstName": "Avni",
        "lastName": "Gupta"
        // ...
      },
      // ...
      {
        "firstName": "Naksh",
        "lastName": "Gupta"
        // ...
      }
    ]
  },
  "page": {
    "size": 20,
    "totalElements": 5,
    "totalPages": 1,
    "number": 0
  }
}
```

### Default REST Endpoints

1. GET - http://localhost:8080/employees
2. GET - http://localhost:8080/employees/{employeeId}
3. POST - http://localhost:8080/employees
4. PUT - http://localhost:8080/employees/{employeeId}
5. DELETE - http://localhost:8080/employees/{employeeId}

### REST Endpoints, After adding base-path property.

spring.data.rest.base-path=/my-api

1. GET - http://localhost:8080/my-api/employees
2. GET - http://localhost:8080/my-api/employees/{employeeId}
3. POST - http://localhost:8080/my-api/employees
4. PUT - http://localhost:8080/my-api/employees/{employeeId}
5. DELETE - http://localhost:8080/my-api/employees/{employeeId}

### REST Endpoints, After adding custom repo path

@RepositoryRestResource(path = "yt-employees")

1. GET - http://localhost:8080/my-api/yt-employees
2. GET - http://localhost:8080/my-api/yt-employees/{employeeId}
3. POST - http://localhost:8080/my-api/yt-employees
4. PUT - http://localhost:8080/my-api/yt-employees/{employeeId}
5. DELETE - http://localhost:8080/my-api/yt-employees/{employeeId}