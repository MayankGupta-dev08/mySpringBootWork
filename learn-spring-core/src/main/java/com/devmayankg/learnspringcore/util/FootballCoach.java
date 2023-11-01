package com.devmayankg.learnspringcore.util;

//@Component
public class FootballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Do dribbling practice for an hour.";
    }
}