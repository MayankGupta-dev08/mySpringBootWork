package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.service.ContactDetailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@SuppressWarnings("unused")
public class ContactController {
    private final ContactDetailService contactDetailService;

    @Autowired
    public ContactController(ContactDetailService contactDetailService) {
        this.contactDetailService = contactDetailService;
    }

    @RequestMapping(value = {"/contact"})
    public String displayContactPage(Model model) {
        model.addAttribute("contactDetail", new ContactDetail());
        return "contact.html";
    }

    @PostMapping(value = {"/saveMsg"})
    public String saveMessage(@Valid @ModelAttribute("contactDetail") ContactDetail contactDetail, Errors errors) {
        if (errors.hasErrors()) {
            log.error("Validation errors occurred in the contact form: {}", errors.getAllErrors());
            return "contact.html";
        }
        contactDetailService.saveContactDetail(contactDetail);
        return "redirect:/contact";
    }
}
