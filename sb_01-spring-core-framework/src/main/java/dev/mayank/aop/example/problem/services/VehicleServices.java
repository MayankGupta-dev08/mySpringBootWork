package dev.mayank.aop.example.problem.services;

import dev.mayank.aop.example.problem.interfaces.Speaker;
import dev.mayank.aop.example.problem.interfaces.Tyres;
import dev.mayank.aop.example.problem.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.logging.Logger;

@Component
@SuppressWarnings("unused")
public class VehicleServices {
    private static final Logger logger = Logger.getLogger(VehicleServices.class.getName());

    private final Speaker speaker;
    private final Tyres tyres;

    @Autowired
    public VehicleServices(Speaker speaker, Tyres tyres) {
        this.speaker = speaker;
        this.tyres = tyres;
    }

    public String playMusic(boolean isVehicleStarted, Song song) {
        Instant startTime = Instant.now();
        logger.info("Playing music...");

        String music = null;
        if (isVehicleStarted)
            music = speaker.makeSound(song);
        else
            logger.warning("Vehicle is not started. Cannot play music.");

        Instant endTime = Instant.now();
        logger.info("Music played in " + (endTime.getEpochSecond() - startTime.getEpochSecond()) + " seconds.");
        System.out.println("**************************************************************");
        return music;
    }

    public String moveVehicle(boolean isVehicleStarted) {
        Instant startTime = Instant.now();
        logger.info("Moving vehicle...");

        String status = null;
        if (isVehicleStarted)
            status = tyres.rotate();
        else
            logger.warning("Vehicle is not started. Cannot move vehicle.");

        Instant endTime = Instant.now();
        logger.info("Vehicle moved in " + (endTime.getEpochSecond() - startTime.getEpochSecond()) + " seconds.");
        System.out.println("**************************************************************");
        return status;
    }

    public String stopVehicle(boolean isVehicleStarted) {
        Instant startTime = Instant.now();
        logger.info("Stopping vehicle...");

        String status = null;
        if (isVehicleStarted)
            status = tyres.stop();
        else
            logger.warning("Vehicle is not started. Cannot stop vehicle.");

        Instant endTime = Instant.now();
        logger.info("Vehicle stopped in " + (endTime.getEpochSecond() - startTime.getEpochSecond()) + " seconds.");
        System.out.println("**************************************************************");
        return status;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyres getTyres() {
        return tyres;
    }
}