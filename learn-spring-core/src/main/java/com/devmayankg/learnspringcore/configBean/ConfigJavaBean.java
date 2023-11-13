package com.devmayankg.learnspringcore.configBean;

import com.devmayankg.learnspringcore.external.SwimCoach;
import com.devmayankg.learnspringcore.util.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config Bean: make an existing third party class available to spring framework
 * So here, we have created a class to use the third party class as a Bean because we can't add @Component to that class
 */
@Configuration
public class ConfigJavaBean {

    @Bean
    public Coach swimCoach() {
        return new SwimCoach();
    }
}