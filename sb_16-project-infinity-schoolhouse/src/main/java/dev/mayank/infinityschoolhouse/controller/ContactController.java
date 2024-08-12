package dev.mayank.infinityschoolhouse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@SuppressWarnings("unused")
public class ContactController {

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact.html";
    }
}
