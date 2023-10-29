package com.mayankg.springboot.learn.myFirstApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {

    // exposing --> / --> as edpoint for our home page
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
}