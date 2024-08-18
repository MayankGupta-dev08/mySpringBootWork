package dev.mayank.infinityschoolhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionController.class);

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        LOGGER.error("An exception occurred: {}", e.getMessage());
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error.html");
        errorPage.addObject("errormsg", e.getMessage());
        return errorPage;
    }
}