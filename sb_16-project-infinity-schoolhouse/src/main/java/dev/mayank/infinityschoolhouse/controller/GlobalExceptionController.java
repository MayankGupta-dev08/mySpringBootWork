package dev.mayank.infinityschoolhouse.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@SuppressWarnings("unused")
public class GlobalExceptionController {

    @ExceptionHandler(Exception.class)
    public ModelAndView handleGenericException(Exception e) {
        ModelAndView errorPage = new ModelAndView();
        errorPage.setViewName("error.html");
        errorPage.addObject("errormsg", e.getMessage());
        return errorPage;
    }
}