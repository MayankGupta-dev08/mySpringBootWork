package dev.mayank.assignment1.services;

import dev.mayank.assignment1.interfaces.Speaker;
import dev.mayank.assignment1.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class VehicleServices {
    private final Speaker speaker;
    private final Tyres tyres;

    @Autowired
    public VehicleServices(Speaker speaker, Tyres tyres) {
        this.speaker = speaker;
        this.tyres = tyres;
    }

    public void playMusic() {
        speaker.makeSound();
    }

    public void moveVehicle() {
        tyres.rotate();
    }
}