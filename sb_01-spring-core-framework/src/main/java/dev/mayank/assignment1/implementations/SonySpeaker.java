package dev.mayank.assignment1.implementations;

import dev.mayank.assignment1.interfaces.Speaker;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@SuppressWarnings("unused")
public class SonySpeaker implements Speaker {

    @Override
    public void makeSound() {
        System.out.println("Sony Speaker is making sound");
    }
}
