package com.mayankg.springboot.learn.myFirstApp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {

    // injecting values in data members using custom app properties
    @Value("${user.name}")
    private String userName;

    @Value("${user.email}")
    private String userEmail;

    // exposing --> / --> as endpoint for our home page
    @GetMapping("/")
    public String homePage() {
        return "Home!!";
    }

    // exposing a new endpoint --> /about --> on new page
    @GetMapping("/about")
    public String aboutPage() {
        return "About Us!!";
    }

    // exposing a new endpoint --> /contact --> on new page
    @GetMapping("/contact")
    public String contactPage() {
        return "Contact Us!!";
    }

    // exposing a new endpoint --> /admin --> on new page
    @GetMapping("/admin")
    public String adminPage() {
        return "Admin Details: " + userName + ", " + userEmail + ".";
    }
}