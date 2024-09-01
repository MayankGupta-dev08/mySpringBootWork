package dev.mayank.infinityschoolhouse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EntityScan(basePackages = "dev.mayank.infinityschoolhouse.model")
@EnableJpaRepositories(basePackages = "dev.mayank.infinityschoolhouse.repository")
public class InfinitySchoolhouse {
    public static void main(String[] args) {
        SpringApplication.run(InfinitySchoolhouse.class, args);
    }
}