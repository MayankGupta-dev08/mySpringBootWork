package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.Person;
import dev.mayank.infinityschoolhouse.model.Profile;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@SuppressWarnings("unused")
public class ProfileController {

    @RequestMapping("/displayProfile")
    public ModelAndView displayProfile(Model model, HttpSession session) {
        Person loggedInPerson = (Person) session.getAttribute("loggedInPerson");
        Profile profile = personToProfileMapper(loggedInPerson);
        ModelAndView modelAndView = new ModelAndView("profile.html");
        modelAndView.addObject("profile", profile);
        return modelAndView;
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
}
