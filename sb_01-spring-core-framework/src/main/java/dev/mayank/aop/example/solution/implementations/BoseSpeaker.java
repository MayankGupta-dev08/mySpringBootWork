package dev.mayank.aop.example.solution.implementations;

import dev.mayank.aop.example.solution.interfaces.Speaker;
import dev.mayank.aop.example.solution.model.Song;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Component
@SuppressWarnings("unused")
public class BoseSpeaker implements Speaker {

    @Override
    public String makeSound(Song song) {
        return MessageFormat.format("Playing {0} by {1} on Bose Speaker", song.name(), song.artist());
    }
}