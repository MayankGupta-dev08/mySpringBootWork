package com.devmayankg.learnspringcore.util;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "do fast bowling practice for an hour.";
    }
}