package dev.mayank.registerbeansmanually.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SuppressWarnings("unused")
@ComponentScan(basePackages = "dev.mayank.registerbeansmanually.beans")
public class ProjectConfig {
}
