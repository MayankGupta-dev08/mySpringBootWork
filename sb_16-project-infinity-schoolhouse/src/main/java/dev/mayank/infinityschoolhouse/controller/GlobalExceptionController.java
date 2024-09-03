package dev.mayank.infinityschoolhouse.controller;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionController {

    @ExceptionHandler(ConstraintViolationException.class)
    public ModelAndView handleConstraintViolationException(ConstraintViolationException ex) {
        log.error("Validation error: {}", ex.getMessage(), ex);
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error.html");
        errorPage.addObject("errormsg", "Validation failed: " + ex.getMessage());
        return errorPage;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        log.error("An error occurred: {}", e.getMessage(), e);
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error.html");
        errorPage.addObject("errormsg", e.getMessage());
        return errorPage;
    }
}