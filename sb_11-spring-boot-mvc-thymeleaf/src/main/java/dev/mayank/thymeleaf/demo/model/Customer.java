package dev.mayank.thymeleaf.demo.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public final class Customer {

    private String firstName;
    private String lastName;
    private String postalCode;
    private int freePasses;
    private String courseCode;

}