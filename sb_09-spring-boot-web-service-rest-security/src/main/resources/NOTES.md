# SpringBoot: RESTFul Service with REST Security

---

## After adding rest.base-path property and @RepositoryRestResource, Custom REST ENDPOINTS

- api --> our-api
- employees --> our-employee

## RESTRICTING URLS --> BASED ON ROLES

1. GET - http://localhost:8080/our-api/our-employees --> Read All ==> CLIENT
2. GET - http://localhost:8080/our-api/our-employees/{employeeId} --> Read single ==> CLIENT
3. POST - http://localhost:8080/our-api/our-employees --> Add single ==> DEVELOPER
4. PUT - http://localhost:8080/our-api/our-employees/{employeeId} --> Update single ==> DEVELOPER
5. DELETE - http://localhost:8080/our-api/our-employees/{employeeId} --> Delete single ==> ADMIN