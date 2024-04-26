package dev.mayank.thymeleaf.demo.model;

import dev.mayank.thymeleaf.demo.validation.CourseCode;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Customer {

    @NotNull(message = "required")
    @Size(min = 1, max = 10, message = "min:1, max:10")
    private String firstName;

    @Size(max = 10, message = "max:10")
    private String lastName;

    @Pattern(regexp = "^[A-Z]{3}[0-9]{2}", message = "first 3 characters to be alphabets, rest 2 should be numeric")
    private String postalCode;

    @Min(value = 0, message = "min = 0")
    @Max(value = 5, message = "max = 5")
    private Integer freePasses;

    @CourseCode(value = "SpringBoot", message = "code must start with SpringBoot")
    @NotNull(message = "required")
    private String courseCode;

}