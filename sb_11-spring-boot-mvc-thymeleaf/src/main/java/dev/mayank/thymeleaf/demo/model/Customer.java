package dev.mayank.thymeleaf.demo.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
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

    @Min(value = 1, message = "at least 1 is required")
    @Min(value = 5, message = "at max 5 is possible")
    private int freePasses;

    private String courseCode;

}