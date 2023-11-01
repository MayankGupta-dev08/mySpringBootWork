package com.devmayankg.learnspringcore.util;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class BasketballCoach implements Coach {
    public BasketballCoach() {
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "practice 3-pointer shooting for an hour.";
    }
}