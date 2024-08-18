package dev.mayank.infinityschoolhouse.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout, Model model) {
        LOGGER.info("Displaying login page");

        String errorMessage = null;
        if (error != null) errorMessage = "Username or Password is incorrect !!";
        if (logout != null) errorMessage = "You have been successfully logged out !!";

        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }
}