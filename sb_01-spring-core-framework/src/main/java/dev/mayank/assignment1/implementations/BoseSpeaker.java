package dev.mayank.assignment1.implementations;

import dev.mayank.assignment1.interfaces.Speaker;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BoseSpeaker implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("Bose Speaker is making sound");
    }
}
