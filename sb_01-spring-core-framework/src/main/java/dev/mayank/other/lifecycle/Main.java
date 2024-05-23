package dev.mayank.other.lifecycle;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("configg.xml");
        Samosa samosa = (Samosa) context.getBean("aSamosa");
        System.out.println(samosa);
        // for destroy method to actually work
        context.registerShutdownHook();
    }
}