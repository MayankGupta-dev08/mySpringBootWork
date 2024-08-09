package dev.mayank.aop.example.problem.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
@ComponentScan(basePackages = {
        "dev.mayank.aop.example.problem.implementations",
        "dev.mayank.aop.example.problem.services"
})
public class AppConfig {
}
