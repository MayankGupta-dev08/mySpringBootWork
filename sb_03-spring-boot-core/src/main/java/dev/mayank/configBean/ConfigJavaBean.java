package dev.mayank.configBean;

import dev.mayank.external.SwimCoach;
import dev.mayank.util.Coach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Java Config Bean: Take an existing third party class or own class and expose it as a Spring Bean.
 * So here, we have created a class to use the third party class as a Bean because we can't add @Component to that class.
 */
@Configuration
class ConfigJavaBean {

    /**
     * Having a custom bean id (swimCoach) for the method initializeSwimCoach()
     */
    @Bean("swimCoach")
    public Coach initializeSwimCoach() {
        return new SwimCoach();
    }
}