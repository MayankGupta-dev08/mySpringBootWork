package com.devmayankg.learnspringcore;

import org.springframework.stereotype.Component;

@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "do fast bowling practice for an hour.";
    }
}