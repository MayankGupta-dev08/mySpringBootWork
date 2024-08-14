package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.service.ContactDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SuppressWarnings("unused")
public class ContactController {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactController.class);
    private final ContactDetailService contactDetailService;

    @Autowired
    public ContactController(ContactDetailService contactDetailService) {
        this.contactDetailService = contactDetailService;
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage() {
        return "contact.html";
    }

    @PostMapping(value = {"/saveMsg"})
    public ModelAndView saveMessage(ContactDetail contactDetail) {
        contactDetailService.saveContactDetail(contactDetail);
        return new ModelAndView("redirect:/contact");
    }
}
