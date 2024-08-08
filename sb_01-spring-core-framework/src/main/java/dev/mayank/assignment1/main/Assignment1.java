package dev.mayank.assignment1.main;

import dev.mayank.assignment1.beans.Person;
import dev.mayank.assignment1.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;

public class Assignment1 {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        String[] personBeans = context.getBeanNamesForType(dev.mayank.assignment1.beans.Person.class);
        System.out.println("Person beans: " + Arrays.toString(personBeans));

        String[] vehicleBeans = context.getBeanNamesForType(dev.mayank.assignment1.beans.Vehicle.class);
        System.out.println("Vehicle beans: " + Arrays.toString(vehicleBeans));

        System.out.println("----------------------------------------");

        var person = context.getBean(Person.class);
        var vehicle = person.getVehicle();
        System.out.printf("%s is driving %s%n", person, vehicle.getName());
        var vehicleService = vehicle.getVehicleServices();
        vehicleService.moveVehicle();
        vehicleService.playMusic();
    }
}
