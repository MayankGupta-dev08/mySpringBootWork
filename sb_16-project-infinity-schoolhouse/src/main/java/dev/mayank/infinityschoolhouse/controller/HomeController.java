package dev.mayank.infinityschoolhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("unused")
public class HomeController {

    @RequestMapping(value = {"", "/", "/home"})
    public String displayHomePage(Model model) {
        model.addAttribute("username", "Learner");
        return "home.html";
    }
}
