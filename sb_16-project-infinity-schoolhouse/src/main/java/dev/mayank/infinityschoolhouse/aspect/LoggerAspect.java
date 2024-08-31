package dev.mayank.infinityschoolhouse.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Slf4j
@Aspect
@Component
@SuppressWarnings("unused")
public class LoggerAspect {

    @Around("execution(* dev.mayank.infinityschoolhouse..*.*(..))")
    public Object logMethodCall(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Method called: {}", joinPoint.getSignature().toShortString());

        Instant start = Instant.now();
        Object result = joinPoint.proceed();
        Instant end = Instant.now();

        log.info("Method execution time: {} ms", end.toEpochMilli() - start.toEpochMilli());
        log.info("Method execution completed: {}", joinPoint.getSignature().toShortString());
        return result;
    }

    @AfterThrowing(pointcut = "execution(* dev.mayank.infinityschoolhouse..*.*(..))", throwing = "exception")
    public void logException(JoinPoint joinPoint, Exception exception) {
        log.error("Exception occurred @{} : {}", joinPoint.getSignature().toShortString(), exception.getMessage());
    }
}