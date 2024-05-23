package dev.mayank.other.injection.constructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class Main {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("config.xml");
        employeeExample(xmlApplicationContext);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        pointExample(xmlApplicationContext);
    }

    private static void employeeExample(ClassPathXmlApplicationContext xmlApplicationContext) {
        Employee employee1 = (Employee) xmlApplicationContext.getBean("employee1");
        System.out.println(employee1);
    }

    private static void pointExample(ClassPathXmlApplicationContext xmlApplicationContext) {
        Point point1 = (Point) xmlApplicationContext.getBean("point1");
        System.out.println(point1);
    }
}