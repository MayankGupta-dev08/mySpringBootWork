package dev.mayank.ishproxyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "dev.mayank.ishproxyservice.proxy")
public class ISHProxyService {
    public static void main(String[] args) {
        SpringApplication.run(ISHProxyService.class, args);
    }
}