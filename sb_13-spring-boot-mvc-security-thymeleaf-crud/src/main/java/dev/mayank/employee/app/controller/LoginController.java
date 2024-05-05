package dev.mayank.employee.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@SuppressWarnings("unused")
public class LoginController {

    /**
     * @link: <a href="http://localhost:8080/showMyLoginPage">showMyLoginPage</a>
     * @return loginForm.html
     */
    @GetMapping("/showMyLoginPage")
    public String showMyLoginPage() {
        return "loginForm";
    }

    /**
     * @link: <a href="http://localhost:8080/access-denied">access-denied</a>
     * @return accessDenied.html
     */
    @GetMapping("/access-denied")
    public String showAccessDenied() {
        return "accessDenied";
    }
}