package dev.mayank.thymeleaf.demo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
public @interface CourseCode {

    /**
     * Default course code
     */
    String value() default "CWM";

    /**
     * Default error message
     */
    String message() default "code must start with CWM";

    /**
     * Default groups
     */
    Class<?>[] groups() default {};

    /**
     * Default error message
     */
    Class<? extends Payload>[] payload() default {};
}