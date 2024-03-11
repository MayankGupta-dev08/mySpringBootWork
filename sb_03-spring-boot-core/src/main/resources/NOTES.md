# Spring Boot Core Notes

----

## Spring Container's responsibility
- **Inversion of Control**: Creating and managing objects (spring beans) and their lifecycle. 
- **Dependency Injection**: Inject Object Dependencies. Based on the Dependency Inversion Principle (Client delegates the responsibility of providing its dependencies on another object.)
  - It Can be achieved in three ways:
    - _XML config file_ (legacy way).
    - _Java Annotations_ (modern)
    - _Java Source Code_ (modern)
- Spring is more than just IoC and DI, it is targeted for real-time enterprise software as it provides features like:
  - _Database access_
  - _Transactions_
  - _REST APIs_
  - _Web MVC_
  - _Security_ and many more...


### Dependency Injection
- **Constructor Injection**: preferred for required dependencies
- **Setter Injection**: preferred for optional dependencies (If a dependency is not provided, then ur app could provide a reasonable default logic.)
- **Field Injection**: not preferred, makes the code harder for unit tests (maybe used in legacy projects). It's accomplished by Java Reflection, and we can do this even on private fields.


### Autowiring in Spring
- For DI, spring can use autowiring (`@Autowired`). In this spring will look for classes that **matches by type** (class or interface) and hence will try to inject it automatically.
- If we have a single constructor, then `@Autowired` is not mandatory and spring will automatically do autowiring.
- Spring will use `@Component` annotation for considering the class as a spring bean. A spring bean is a regular java class that is managed by Spring. 
  - `@Component` also makes the bean available for the DI.
- In case more than one class is matched, then either we need to handle it or if not handled, spring will throw exception.
- We can handle a multiple match scenario using `@Primary` or `@Qualifier` annotations. 
  - We can have only one `@Primary` for a type of class/interface.
  - We can use `@Qualifier` in case of setter injections and have more than 1 `@Qualifier`s for a same type by mentioning different name for the qualifiers.
  - `@Qualifier` has higher priority and more specific than `@Primary`.
- We can inject dependencies by calling any method in our class by simply giving `@Autowired` for that method.


### Spring Beans Initialisation
- By default, when the application starts, all the beans are initialized.
- Spring will create an instance of each and make them available. The scope for a bean is _Singleton_ by default.
- Instead of creating all beans up front, we can specify lazy initialization for beans by adding `@Lazy` on top of their class.
  - This feature is disabled by default.
  - In this case, a bean will only be initialized if it's needed for DI, or it is explicitly requested.
  - We can set a global property in our **application.properties** file as _spring.main.lazy-initialization=true_
    - This will make all the beans lazy, including the RestController Class.
- _Prototype_ beans are lazy by default. There is no need to use the `@Lazy` annotation for prototype scopes beans.
- Advantages of Lazy Initialization:
  - Only create objects when needed. --> May help with faster startup time if there are many components.
- Disadvantages of Lazy Initialization:
  - If we have web related components like `@RestConstroller`, not created until requested.
  - May not discover configuration issues until too late.
  - We need to make sure we have enough memory once all beans are created.


### Bean Scopes
- Refers to the lifecycle of the bean (how long it will live and how many instances can be created and how is it shared).
- Different Spring Bean Scopes:
    - Singleton: Creates a single shared instance of the bean. Default Scope
    - Prototype: Creates a new instance of the bean for each container request.
    - Request: Scoped to an HTTP web request. Only used for web apps.
    - Session: Scoped to an HTTP web session. Only used for web apps.
    - Global-Session: Scoped to a global HTTP web session. Only used for web apps.
- Eg: `@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)`.
- Bean Lifecycle Methods/Hooks: we can call custom business logic methods at bean initialization and bean destruction
  - `@PostConstruct`: init method — can be used for setting up handles to resources (db, sockets, files)
  - `@PreDestroy`: destroy method — can be used for clean up handles to resources (db, sockets, files)
- For "prototype" scoped beans, Spring does not call the destroy method. Gasp! The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.


### Use External Service as a Bean
- We can't edit the jars of the external service, so can't add `@Component` to its class.
- We can achieve this using the below approach:
  - Use `@Configuration` on a class for using it as JavaBeanConfig class and then create a method which returns the new object of that service class and mark that method with `@Bean` annotation. Inject wherever its needed using type match or autowiring.
- By default, the id of the bean is the same as the method name. We can override this by manually adding `@Bean(name="beanName")`.

### Some other important annotations and their usage
1. **@SpringBootApplication**:
    - This annotation is used to mark the main class of a Spring Boot application. It combines three other annotations:
        - `@Configuration`: Indicates that the class contains Spring configuration. Ables us to register extra beans with `@Bean`.
        - `@EnableAutoConfiguration`: Enables Spring Boot's auto-configuration feature, which automatically configures the Spring application based on its classpath and other configurations.
        - `@ComponentScan`: Tells Spring to scan the specified packages for components, configurations, and other Spring-managed beans. Also, recursively scan the sub-packages of the current package mentioned or where your main SpringBootApplication is present.
    - `@SpringBootApplication` is a convenient way to enable these three annotations in a single declaration.
    - If we want to include some other package also for _ComponentScanning_ then we could add their path in the scanBasePackages list.
      - `@SpringBootApplication(scanBasePackages={"dev.mayank", "dev.technoSorcerer", "dev.mridul"})`

2. **@Configuration**:
    - This annotation is used to indicate that a class contains Spring configuration. It's typically used in conjunction with `@Bean` methods to define Spring beans explicitly.
    - Classes annotated with `@Configuration` are often used to define beans and their dependencies in a Java-based Spring configuration instead of XML files.

3. **@ComponentScan**:
    - This annotation is used to specify the base packages that Spring should scan for components, configurations, and other Spring-managed beans.
    - Spring scans these packages for classes annotated with stereotypes like `@Component`, `@Service`, `@Repository`, and `@Controller`, and automatically registers them as Spring beans.

4. **@EnableAutoConfiguration**:
      - This annotation is used to enable Spring Boot's auto-configuration feature. It automatically configures the Spring application based on its dependencies and the environment.
      - Spring Boot auto-configuration attempts to automatically configure the Spring application by guessing the required beans based on the classpath and other configurations.

5. **@RestController**:
     - This annotation is used to mark a class as a controller in a Spring MVC application. It combines the `@Controller` and `@ResponseBody` annotations.
     - `@Controller` indicates that the class serves as a controller for handling HTTP requests.
     - `@ResponseBody` tells Spring MVC to serialize the return value of the methods in the controller directly to the HTTP response body.

6. **@GetMapping**, **@PostMapping**, **@PutMapping**, **@DeleteMapping**:
     - These annotations are used to map HTTP requests to handler methods in Spring MVC controllers.
     - `@GetMapping` maps HTTP GET requests to specific handler methods.
     - `@PostMapping` maps HTTP POST requests.
     - `@PutMapping` maps HTTP PUT requests.
     - `@DeleteMapping` maps HTTP DELETE requests.
     - These annotations help define the mapping between URLs and controller methods in a RESTful API.