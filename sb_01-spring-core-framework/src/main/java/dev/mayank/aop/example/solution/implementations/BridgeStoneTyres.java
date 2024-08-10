package dev.mayank.aop.example.solution.implementations;

import dev.mayank.aop.example.solution.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BridgeStoneTyres implements Tyres {

    @Override
    public String rotate() {
        return "BridgeStone Tyres are rotating";
    }

    @Override
    public String stop() {
        return "BridgeStone Tyres have stopped";
    }
}