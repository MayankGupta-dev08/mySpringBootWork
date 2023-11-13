package com.devmayankg.learnspringcore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // @EnableAutoConfiguration, @ComponentScan, @Configuration
public class LearnSpringCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnSpringCoreApplication.class, args);
    }
}