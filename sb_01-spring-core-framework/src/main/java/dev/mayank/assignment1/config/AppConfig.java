package dev.mayank.assignment1.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(
        basePackages = {"dev.mayank.assignment1.implementations", "dev.mayank.assignment1.services"},
        basePackageClasses = {dev.mayank.assignment1.beans.Person.class, dev.mayank.assignment1.beans.Vehicle.class}
)
@SuppressWarnings("unused")
public class AppConfig {
}