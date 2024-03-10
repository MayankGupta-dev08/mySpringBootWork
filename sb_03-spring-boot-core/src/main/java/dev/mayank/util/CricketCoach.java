package dev.mayank.util;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**Prototype beans are lazy by default. There is no need to use the @Lazy annotation for prototype scopes beans.*/
@Component
//@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)   //by default --> only single instance for a bean
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class CricketCoach implements Coach {

    public CricketCoach() {
        System.out.println("Constructor initiated of " + getClass().getSimpleName());
    }

    /** post initialization of bean */
    @PostConstruct
    private void init() {
        System.out.println("In startUp method of " + getClass().getSimpleName());
    }

    /** pre destruction of bean */
    /**
     * For "prototype" scoped beans, Spring does not call the destroy method. Gasp!
     * The client code must clean up prototype-scoped objects and release expensive resources that the prototype bean(s) are holding.
     * */
    @PreDestroy
    private void cleanUp() {
        System.out.println("In cleanUp method of " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Do batting/bowling practice for an hour.";
    }
}