package dev.mayank.infinityschoolhouse.validations;

import dev.mayank.infinityschoolhouse.annotations.PasswordValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class PasswordStrengthValidator implements ConstraintValidator<PasswordValidator, String> {
    List<String> weakPasswords;

    @Override
    public void initialize(PasswordValidator constraintAnnotation) {
        weakPasswords = List.of(
                "12345", "password", "qwerty", "123456", "12345678", "abc123", "111111", "123123", "admin", "54321",
                "welcome", "monkey", "1234", "123456789", "1234567", "sunshine", "iloveyou", "princess", "football",
                "baseball", "dragon", "master", "hello", "freedom", "whatever", "qazwsx", "trustno1"
        );
    }

    @Override
    public boolean isValid(String passwordField, ConstraintValidatorContext context) {
        return passwordField != null && (!weakPasswords.contains(passwordField));
    }
}