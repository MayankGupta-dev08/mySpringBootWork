package dev.mayank.employee.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
public class LoginController {

    /**
     * @return homePage.html
     */
    @GetMapping("/")
    public String showHome() {
        return "homePage";
    }

    /**
     * @return leadersPage.html
     */
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leadersPage";
    }

    /**
     * @return systemsPage.html
     */
    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }
}