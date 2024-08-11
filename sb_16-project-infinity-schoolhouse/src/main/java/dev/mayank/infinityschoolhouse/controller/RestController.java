package dev.mayank.infinityschoolhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RestController {

    @RequestMapping("/home")
    public String displayHomePage() {
        return "home.html";
    }
}
