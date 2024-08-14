package dev.mayank.aop.example.solution.implementations;

import dev.mayank.aop.example.solution.interfaces.Speaker;
import dev.mayank.aop.example.solution.model.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;

@Primary
@Component
@SuppressWarnings("unused")
public class SonySpeaker implements Speaker {

    @Override
    public String makeSound(Song song) {
        return MessageFormat.format("Playing ''{0}'' by {1} on Sony Speaker", song.name().toUpperCase(), song.artist());
    }
}