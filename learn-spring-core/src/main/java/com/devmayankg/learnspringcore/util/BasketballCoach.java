package com.devmayankg.learnspringcore.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BasketballCoach implements Coach {
    public BasketballCoach() {
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
        return "practice 3-pointer shooting for an hour.";
    }
}