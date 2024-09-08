package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Address;
import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.model.Profile;
import dev.mayank.infinityschoolhouse.repository.PersonRepository;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@SuppressWarnings("unused")
public class ProfileController {

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session) {
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        Profile profile = personToProfileMapper(loggedInPerson);
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
    }

    @PostMapping(value = "/updateProfile")
    public String updateProfile(@Valid @ModelAttribute("profile") Profile profile, Errors errors, HttpSession session) {
        if (errors.hasErrors()) return "profile.html";

        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        profileToPersonMapper(profile, loggedInPerson);
        personRepository.save(loggedInPerson);  // Save the updated profile to the database
        session.setAttribute("loggedInPerson", loggedInPerson);
        return "redirect:/displayProfile";
    }

    private Profile personToProfileMapper(Person loggedInPerson) {
        Profile profile = new Profile();
        profile.setName(loggedInPerson.getName());
        profile.setMobileNumber(loggedInPerson.getMobileNumber());
        profile.setEmail(loggedInPerson.getEmail());
        if (loggedInPerson.getAddress() != null && loggedInPerson.getAddress().getAddressId() > 0) {
            profile.setAddress1(loggedInPerson.getAddress().getAddress1());
            profile.setAddress2(loggedInPerson.getAddress().getAddress2());
            profile.setCity(loggedInPerson.getAddress().getCity());
            profile.setState(loggedInPerson.getAddress().getState());
            profile.setZipCode(loggedInPerson.getAddress().getZipCode());
        }
        return profile;
    }

    private void profileToPersonMapper(Profile profile, Person loggedInPerson) {
        loggedInPerson.setName(profile.getName());
        loggedInPerson.setEmail(profile.getEmail());
        loggedInPerson.setMobileNumber(profile.getMobileNumber());
        if (loggedInPerson.getAddress() == null || !(loggedInPerson.getAddress().getAddressId() > 0)) {
            loggedInPerson.setAddress(new Address());
        }
        loggedInPerson.getAddress().setAddress1(profile.getAddress1());
        loggedInPerson.getAddress().setAddress2(profile.getAddress2());
        loggedInPerson.getAddress().setCity(profile.getCity());
        loggedInPerson.getAddress().setState(profile.getState());
        loggedInPerson.getAddress().setZipCode(profile.getZipCode());
    }
}
