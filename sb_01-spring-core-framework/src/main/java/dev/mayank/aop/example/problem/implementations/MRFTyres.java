package dev.mayank.aop.example.problem.implementations;

import dev.mayank.aop.example.problem.interfaces.Tyres;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Primary
@Component
@SuppressWarnings("unused")
public class MRFTyres implements Tyres {

    @Override
    public String rotate() {
        return "MRF Tyres are rotating";
    }

    @Override
    public String stop() {
        return "MRF Tyres have stopped";
    }
}
