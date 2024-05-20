package dev.mayank.other.injection.setter;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudentMain {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("studentConfig.xml");
        Student student1 = (Student) applicationContext.getBean("student1");
        System.out.println(student1);
    }
}