package com.devmayankg.learnspringcore.util;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)   //by default --> only single instance for a bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "do fast bowling practice for an hour.";
    }
}