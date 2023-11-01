package com.devmayankg.learnspringcore.util;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class FootballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Do dribbling practice for an hour.";
    }
}