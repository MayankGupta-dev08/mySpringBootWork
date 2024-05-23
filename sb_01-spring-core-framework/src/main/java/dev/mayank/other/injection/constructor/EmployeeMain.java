package dev.mayank.other.injection.constructor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

class EmployeeMain {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext xmlApplicationContext = new ClassPathXmlApplicationContext("employeeConfig.xml");
        Employee employee1 = (Employee) xmlApplicationContext.getBean("employee1");
        System.out.println(employee1);
    }
}