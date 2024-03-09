package dev.mayank.emailApp.v3;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
class BeanLifeCycleHooks {
    @PostConstruct
    public void init() {
        System.out.println("~ Inside init JavaLifecycle Hooks ~");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("~ Inside destroy JavaLifecycle Hooks ~");
    }
}