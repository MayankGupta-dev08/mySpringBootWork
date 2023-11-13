package com.devmayankg.learnspringcore.external;

import com.devmayankg.learnspringcore.util.Coach;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**Suppose this is an existing third party class in which you cannot make any change*/
public class SwimCoach implements Coach {

    public SwimCoach() {
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
        return "Swim 1000 meters as a warm up.";
    }
}