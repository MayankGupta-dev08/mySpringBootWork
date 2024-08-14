package dev.mayank.aop.example.solution.main;

import dev.mayank.aop.example.solution.config.ProjectConfig;
import dev.mayank.aop.example.solution.model.Song;
import dev.mayank.aop.example.solution.services.VehicleServices;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * This class is the main class for the AOP solution.
 */
public class AOPSolution {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(ProjectConfig.class)) {
            var vehicleService = context.getBean(VehicleServices.class);
            System.out.println(vehicleService.getClass().getSimpleName());

            System.out.println("------------------------------------------------------------------------------------------------------------------------");

            Song mySong = new Song("Light Switch", "Charlie Puth");
            boolean isVehicleStarted = true;

            vehicleService.moveVehicle(isVehicleStarted);
            vehicleService.playMusic(isVehicleStarted, mySong);
            vehicleService.stopVehicle(isVehicleStarted);
        }
    }
}