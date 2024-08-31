package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.service.ContactDetailService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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

    @RequestMapping(value = {"/displayMessages"})
    public ModelAndView displayOpenMessages() {
        log.info("Displaying all 'OPEN' messages");

        ModelAndView modelAndView = new ModelAndView();
        List<ContactDetail> messagesWithOpenStatus = contactDetailService.getAllMessagesWithOpenStatus();
        modelAndView.addObject("openMessages", messagesWithOpenStatus);
        modelAndView.setViewName("messages.html");

        log.info("Returning all 'OPEN' messages");
        return modelAndView;
    }

    @GetMapping(value = {"/closeMsg"})
    public String closeMessage(@RequestParam("id") int id, Authentication authentication) {
        log.info("Closing message with id: {}", id);
        boolean isUpdated = contactDetailService.updateMessageStatus(id, authentication.getName());

        if ((!isUpdated)) log.error("Failed to close message with id: {}", id);
        else log.info("Message with id: {} closed successfully", id);

        return "redirect:/displayMessages";
    }
}
