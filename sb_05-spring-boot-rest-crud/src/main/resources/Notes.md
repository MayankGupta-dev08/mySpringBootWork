# Spring Boot: RESTful API Exception Handling

---

## REST APIs and Services

REST (Representational State Transfer) is an architectural style for designing networked applications. RESTful APIs (Application Programming Interfaces) adhere to the principles of REST, providing a standardized way for systems to communicate over HTTP.

### Key Concepts of REST APIs:

1. **Resources**: In REST, everything is considered a resource, which can be accessed via a unique identifier (URI). Resources can be data entities, such as users, products, or orders.
2. **HTTP Methods**: RESTful APIs use HTTP methods (GET, POST, PUT, DELETE) to perform operations on resources. For example, GET retrieves a resource, POST creates a new resource, PUT updates an existing resource, and DELETE removes a resource.
3. **Stateless Communication**: REST is stateless, meaning each request from a client to a server must contain all the information necessary to understand and fulfill the request. The server does not maintain any client state between requests.
4. **Uniform Interface**: REST APIs have a uniform interface, which simplifies interactions between clients and servers. This interface typically includes resource URIs, HTTP methods, representations of resources (JSON, XML), and hypermedia links.

### RESTful Services:

RESTful services are web services that adhere to the REST architectural style. They expose resources via HTTP endpoints and follow REST principles for communication. These services are scalable, flexible, and easy to integrate with various platforms and technologies.

## HTTP and Its Components

HTTP (Hypertext Transfer Protocol) is the foundation of data communication on the World Wide Web. It is a stateless protocol that allows web browsers and servers to communicate and exchange data.

### Components of HTTP:

1. **Request Methods**: HTTP defines several request methods (also known as HTTP verbs) that indicate the desired action to be performed on a resource. Common methods include GET, POST, PUT, DELETE, and PATCH.
2. **Status Codes**: HTTP status codes are three-digit numbers returned by a server to indicate the status of a client's request. Examples include 200 (OK), 404 (Not Found), 500 (Internal Server Error), etc.
3. **Headers**: HTTP headers are key-value pairs included in both request and response messages to provide additional information about the message or the resource being transferred. Headers can include information such as authentication credentials, host, user-agent, accept, authorization, content-type, content-length, date, set-cookie, caching directives, etc.
4. **Body**: The body of an HTTP message contains the data being transferred between the client and the server. In requests, the body typically contains data sent to the server, such as form data or JSON payloads. In responses, the body contains the requested resource or an error message.

## Jackson JSON Binding

- It is also known as ̥Serialization-Deserialization or Marshalling-Unmarshalling.
- Jackson is a popular Java library for JSON serialization and deserialization. It provides support for converting Java objects to JSON (and vice versa) and is widely used in web development for processing JSON data.

### Key Features of Jackson:

1. **POJO (Plain Old Java Object) Support**: Jackson allows you to map Java objects to JSON and JSON to Java objects effortlessly. You can annotate Java classes and fields to customize the serialization/deserialization process.
2. **Streaming API**: Jackson provides a low-level streaming API for incremental processing of JSON data. This is useful for handling large JSON documents efficiently.
3. **Tree Model**: Jackson's tree model allows you to parse JSON into a hierarchical data structure (similar to XML DOM) and navigate/manipulate the structure programmatically.
4. **Data Binding**: Jackson's data binding feature automatically maps JSON data to Java objects based on their structure. It supports various data types, collections, and generics.
5. **Annotation Support**: Jackson supports annotations like @JsonProperty, @JsonCreator, @JsonIgnore, etc., for fine-grained control over the serialization/deserialization process.

In summary, Jackson simplifies the handling of JSON data in Java applications, making it easy to integrate with RESTful APIs and web services

### Spring and Jackson Support

Spring Framework provides seamless integration with Jackson, the popular Java library for JSON serialization and deserialization. This integration simplifies the handling of JSON data in Spring-based applications.

#### 1. Jackson Dependency

To use Jackson with Spring, you need to include the Jackson libraries as dependencies in your project. Spring Boot typically includes Jackson libraries by default, so you don't need to explicitly add them. However, if you're using plain Spring MVC, you can include Jackson dependencies in your `pom.xml` (if using Maven) or `build.gradle` (if using Gradle).

#### 2. JSON Conversion in Spring MVC

In Spring MVC, Jackson automatically converts Java objects to JSON and vice versa when handling HTTP requests and responses. If you return a Java object from a controller method with `@ResponseBody` annotation, Spring MVC uses Jackson to convert it to JSON before sending it as the HTTP response body. Similarly, if you receive JSON data in a controller method parameter annotated with `@RequestBody`, Spring MVC uses Jackson to deserialize the JSON into a Java object.

```java
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }
}
```

#### 3. Customization with Jackson Annotations

̥Jackson provides various annotations that you can use to customize the JSON serialization and deserialization process. These annotations allow you to control property names, handle null values, ignore properties, and more. You can annotate your Java classes or fields with these annotations to influence how they are represented as JSON.

```java
public class User {

    @JsonProperty("user_id")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    @JsonIgnore
    private String password;

    // Getters and setters
}
```

#### 4. Integration with Spring Boot

Spring Boot simplifies the configuration of Jackson through application properties. You can customize Jackson's behavior by setting properties in the application.properties or application.yml file. For example, you can configure date formats, enable/disable features, and modify serialization options using properties prefixed with `spring.jackson`.

```properties
# Example application.properties configuration for Jackson
spring.jackson.serialization.indent-output=true
spring.jackson.date-format=yyyy-MM-dd HH:mm:ss
```

## Exception Handling in Spring Boot RESTful APIs

Exception handling is a crucial aspect of building robust and user-friendly RESTful APIs. In Spring Boot, we can handle exceptions gracefully using various techniques. Let's explore how to handle exceptions in a Spring Boot application that exposes RESTful APIs for managing student data.

1. Define Custom Exceptions
2. Create Error Response Structure
3. Implement Exception Handling
4. Integrate with REST Controller

## Some Important Annotations

### `@ControllerAdvice` Annotation

The `@ControllerAdvice` annotation in Spring MVC provides a centralized mechanism for handling exceptions and defining global model attributes across all controllers in the application.

- **Global Exception Handling**: Defines methods that handle exceptions thrown by any controller in the application, eliminating the need for duplicate exception handling code.
- **Centralized Configuration**: Simplifies maintenance and ensures consistency by providing a single location to configure exception handling and model attributes.
- **Fine-Grained Control**: Allows specifying which exceptions a particular `@ControllerAdvice` class should handle using `@ExceptionHandler`, enabling precise control over exception handling logic.
- **Global Model Attributes**: Can define model attributes that are added to every model before rendering the view, facilitating the addition of common attributes like user authentication status or application settings.
- **Flexible Configuration**: Supports additional functionality such as data binding initialization and model attribute population through annotations like `@InitBinder` and `@ModelAttribute`.

### `@ExceptionHandler` Annotation

The `@ExceptionHandler` annotation is used within `@ControllerAdvice` classes to define methods that handle specific types of exceptions.

- **Fine-Grained Exception Handling**: Annotates methods in `@ControllerAdvice` classes to handle specific exceptions thrown by controllers.
- **Customized Exception Handling**: Allows writing custom exception handling logic for each type of exception, providing flexibility in how exceptions are handled.
- **Centralized Exception Handling**: Integrates with `@ControllerAdvice` to centralize exception handling across controllers, reducing code duplication and promoting consistency.
- **Fallback Handling**: Provides a fallback mechanism for handling exceptions that are not handled by individual controllers, improving the robustness of the application.
- **Exception Logging and Reporting**: Enables logging or reporting of exceptions at a global level, aiding in debugging and monitoring efforts.

### `@RequestMapping` Annotation

The `@RequestMapping` annotation in Spring MVC is used to map web requests to specific handler methods in controllers. It allows you to define the URL patterns and HTTP methods that a controller method can handle.

- **URL Mapping**: Specifies the URL patterns that a controller method should respond to. You can use placeholders, regex patterns, and variable paths to define flexible URL mappings.
- **HTTP Method Mapping**: Specifies the HTTP methods (GET, POST, PUT, DELETE, etc.) that a controller method can handle. You can use multiple `@RequestMapping` annotations with different HTTP methods to map the same URL pattern to different handler methods for different HTTP methods.
- **Request Parameters and Headers**: Allows mapping based on request parameters, headers, and other request attributes using additional attributes of the `@RequestMapping` annotation such as params and headers.
- **Content Negotiation**: Supports content negotiation for handling different media types (JSON, XML, HTML, etc.) by specifying the produces and consumes attributes in the @RequestMapping annotation.
- **Path Variables**: Allows extracting path variables from the URL pattern and passing them as method parameters using `@PathVariable` annotation.

In summary, `@RequestMapping` is a versatile annotation in Spring MVC for mapping web requests to controller methods based on URL patterns, HTTP methods, request parameters, headers, and other attributes, providing a flexible and powerful mechanism for handling incoming HTTP requests.