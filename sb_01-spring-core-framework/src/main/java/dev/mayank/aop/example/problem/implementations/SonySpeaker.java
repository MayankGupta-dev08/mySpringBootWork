package dev.mayank.aop.example.problem.implementations;

import dev.mayank.aop.example.problem.interfaces.Speaker;
import dev.mayank.aop.example.problem.model.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@SuppressWarnings("unused")
public class SonySpeaker implements Speaker {

    @Override
    public String makeSound(Song song) {
        return "Playing \'" + song.name().toUpperCase() + "\' by " + song.artist() + " on Sony Speaker";
    }
}
