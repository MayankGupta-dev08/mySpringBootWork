package dev.mayank.assignment1.implementations;

import dev.mayank.assignment1.interfaces.Tyres;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("unused")
public class BridgeStoneTyres implements Tyres {

    @Override
    public void rotate() {
        System.out.println("BridgeStone Tyres are rotating");
    }
}
