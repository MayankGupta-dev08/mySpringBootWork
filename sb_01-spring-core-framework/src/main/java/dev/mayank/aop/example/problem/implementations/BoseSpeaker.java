package dev.mayank.aop.example.problem.implementations;

import dev.mayank.aop.example.problem.interfaces.Speaker;
import dev.mayank.aop.example.problem.model.Song;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BoseSpeaker implements Speaker {

    @Override
    public String makeSound(Song song) {
        return "Playing " + song.name() + " by " + song.artist() + " on Bose Speaker";
    }
}
