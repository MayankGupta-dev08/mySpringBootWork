package dev.mayank.assignment1.implementations;

import dev.mayank.assignment1.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@SuppressWarnings("unused")
public class MRFTyres implements Tyres {

    @Override
    public void rotate() {
        System.out.println("MRF Tyres are rotating");
    }
}
