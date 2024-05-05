package dev.mayank.employee.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
public class EmployeeController {

    /**
     * @link: <a href="http://localhost:8080/">home</a>
     * @return homePage.html
     */
    @GetMapping("/")
    public String showHome() {
        return "homePage";
    }

    /**
     * @link: <a href="http://localhost:8080/leaders">leaders</a>
     * @return leadersPage.html
     */
    @GetMapping("/leaders")
    public String showLeaders() {
        return "leadersPage";
    }

    /**
     * @link: <a href="http://localhost:8080/systems">systems</a>
     * @return systemsPage.html
     */
    @GetMapping("/systems")
    public String showSystems() {
        return "systems";
    }
}