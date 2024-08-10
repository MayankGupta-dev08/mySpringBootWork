package dev.mayank.aop.example.solution.services;

import dev.mayank.aop.example.solution.interfaces.Speaker;
import dev.mayank.aop.example.solution.interfaces.Tyres;
import dev.mayank.aop.example.solution.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleServices {

    private final Speaker speaker;
    private final Tyres tyres;

    @Autowired
    public VehicleServices(Speaker speaker, Tyres tyres) {
        this.speaker = speaker;
        this.tyres = tyres;
    }

    public String playMusic(boolean isVehicleStarted, Song song) {
        //throw new UnsupportedOperationException("Method not implemented");
        return speaker.makeSound(song);
    }

    public String moveVehicle(boolean isVehicleStarted) {
        return tyres.rotate();
    }

    public String stopVehicle(boolean isVehicleStarted) {
        return tyres.stop();
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public Tyres getTyres() {
        return tyres;
    }
}