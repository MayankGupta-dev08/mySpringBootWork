package dev.mayank.aop.example.problem.main;

import dev.mayank.aop.example.problem.config.AppConfig;
import dev.mayank.aop.example.problem.model.Song;
import dev.mayank.aop.example.problem.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class is the main class for the AOP problem.
 */
public class AOPProblem {
    public static void main(String[] args) {
        var context = new AnnotationConfigApplicationContext(AppConfig.class);

        var vehicleService = context.getBean(VehicleServices.class);
        System.out.println(vehicleService.getClass().getSimpleName());

        System.out.println("------------------------------------------------------------------------------------------------------------------------");

        Song mySong = new Song("Trampoline", "SHAED");
        boolean isVehicleStarted = Math.random() < 0.5; // Randomly set the vehicle status to start or not started

        System.out.println(vehicleService.moveVehicle(isVehicleStarted));
        System.out.println(vehicleService.playMusic(isVehicleStarted, mySong));
        System.out.println(vehicleService.stopVehicle(isVehicleStarted));
    }
}
