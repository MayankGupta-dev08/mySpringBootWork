package dev.mayank.registerbeansmanually.main;

import dev.mayank.registerbeansmanually.beans.Vehicle;
import dev.mayank.registerbeansmanually.config.ProjectConfig;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

public class Main {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(ProjectConfig.class);

        Supplier<Vehicle> jaguarSupplier = () -> new Vehicle("Jaguar");

        Supplier<Vehicle> audiSupplier = () -> {
            Vehicle audii = new Vehicle("Audii");
            return audii;
        };

        int randomNumber = (int) (Math.random() * 10);
        System.out.println("randomNumber = " + randomNumber);

        if ((randomNumber % 2) == 0) {
            context.registerBean("jaguar", Vehicle.class, jaguarSupplier);  // registering beans manually using supplier
        } else {
            context.registerBean("audi", Vehicle.class, audiSupplier);
        }

        Vehicle jaguarVehicle = null;
        Vehicle audiVehicle = null;

        try {
            jaguarVehicle = context.getBean("jaguar", Vehicle.class);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.err.println("Error while creating Jaguar vehicle");
        }

        try {
            audiVehicle = context.getBean("audi", Vehicle.class);
        } catch (NoSuchBeanDefinitionException noSuchBeanDefinitionException) {
            System.err.println("Error while creating Audi vehicle");
        }

        if (null != jaguarVehicle) {
            System.out.println("Programming Vehicle name from Spring Context is: " + jaguarVehicle.getName());
        } else {
            System.out.println("Programming Vehicle name from Spring Context is: " + audiVehicle.getName());
        }
    }
}
