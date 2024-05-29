package dev.mayank.other.autowire.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("autoConfig.xml");
        Person person1 = context.getBean("p1", Person.class);
        System.out.println(person1);
    }
}