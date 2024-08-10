package dev.mayank.aop.example.solution.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.logging.Logger;

@Aspect
@Order(2)
@Component
@SuppressWarnings("unused")
public class LoggerAspect {
    private static final Logger LOGGER = Logger.getLogger(LoggerAspect.class.getName());

    @Around("execution(* dev.mayank.aop.example.solution.services.*.*(..))")
    public void log(ProceedingJoinPoint joinPoint) throws Throwable {
        LOGGER.info("Method %s is called.".formatted(joinPoint.getSignature().getName()));
        Instant startTime = Instant.now();

        joinPoint.proceed();

        Instant endTime = Instant.now();
        long elapsedTime = endTime.getEpochSecond() - startTime.getEpochSecond();
        LOGGER.info("Method %s is completed in %d seconds.\n".formatted(joinPoint.getSignature().getName(), elapsedTime));
    }

    @AfterThrowing(pointcut = "execution(* dev.mayank.aop.example.solution.services.*.*(..))", throwing = "e")
    public void logException(JoinPoint joinPoint, Throwable e) {
        LOGGER.severe("Method %s threw an exception: %s.".formatted(joinPoint.getSignature().getName(), e.getMessage()));
    }

    @AfterReturning(pointcut = "execution(* dev.mayank.aop.example.solution.services.*.*(..))", returning = "result")
    public void logSuccess(JoinPoint joinPoint, Object result) {
        LOGGER.info("Method %s is completed successfully with result= %s.".formatted(joinPoint.getSignature().getName(), result));
    }
}
