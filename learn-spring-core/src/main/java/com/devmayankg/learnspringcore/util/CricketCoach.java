package com.devmayankg.learnspringcore.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
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

    //post initialisation of bean
    @PostConstruct
    private void init() {
        System.out.println("In startUp method of " + getClass().getSimpleName());
    }

    //pre destruction of bean
    /**
     * For "prototype" scoped beans, Spring does not call the destroy method. Gasp!
     * */
    @PreDestroy
    private void cleanUp() {
        System.out.println("In cleanUp method of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "do fast bowling practice for an hour.";
    }
}