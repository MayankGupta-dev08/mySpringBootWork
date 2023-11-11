package com.devmayankg.learnspringcore.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FootballCoach implements Coach {

    public FootballCoach() {
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
    }

    //post initialisation of bean
    @PostConstruct
    private void init() {
        System.out.println("In startUp method of " + getClass().getSimpleName());
    }

    //pre destruction of bean
    @PreDestroy
    private void cleanUp() {
        System.out.println("In cleanUp method of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Do dribbling practice for an hour.";
    }
}