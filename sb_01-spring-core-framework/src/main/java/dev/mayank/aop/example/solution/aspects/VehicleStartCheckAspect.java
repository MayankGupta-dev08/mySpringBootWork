package dev.mayank.aop.example.solution.aspects;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Order(1)
@Component
@SuppressWarnings("unused")
public class VehicleStartCheckAspect {
    private static final Logger LOGGER = Logger.getLogger(VehicleStartCheckAspect.class.getName());

    @Before("execution(* dev.mayank.aop.example.solution.services.*.*(..)) && args(isVehicleStarted,..)")
    public void checkVehicleStarted(boolean isVehicleStarted) {
        if (!isVehicleStarted) {
            LOGGER.warning("Vehicle is not started. Cannot proceed.\n");
            throw new RuntimeException("Vehicle is not started.");
        }
    }
}
