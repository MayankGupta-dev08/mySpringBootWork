package com.devmayankg.learnspringcore.util;

import org.springframework.stereotype.Component;

@Component
public class TennisCoach implements Coach {

    public TennisCoach() {
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "practice smashing for an hour.";
    }
}