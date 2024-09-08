package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.service.PersonService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("public")
@SuppressWarnings("unused")
public class PublicController {

    @Autowired
    PersonService personService;

    @GetMapping(value = {"/register"})
    public String displayRegisterPage(Model model) {
        model.addAttribute("person", new Person());
        return "register.html";
    }

    @PostMapping(value = {"/createUser"})
    public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Validation errors occurred in the registration form: {}", errors.getAllErrors());
            return "register.html";
        }
        boolean isSaved = personService.createNewPerson(person);
        if (isSaved)
            return "redirect:/login?register=true";
        return "register.html";
    }
}
