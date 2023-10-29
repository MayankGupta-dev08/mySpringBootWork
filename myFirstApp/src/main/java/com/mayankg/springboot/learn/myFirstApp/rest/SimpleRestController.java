package com.mayankg.springboot.learn.myFirstApp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
//    exposing --> / (root of webpage) to return Hello World

    @GetMapping("/")
    public String sayHello() {
        return "Hello World!!";
    }
}