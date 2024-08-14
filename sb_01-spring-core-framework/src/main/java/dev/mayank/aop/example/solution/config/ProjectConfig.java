package dev.mayank.aop.example.solution.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@SuppressWarnings("unused")
@ComponentScan(basePackages = {
        "dev.mayank.aop.example.solution.implementations",
        "dev.mayank.aop.example.solution.services",
        "dev.mayank.aop.example.solution.aspects"
})
@EnableAspectJAutoProxy
public class ProjectConfig {
}