package dev.mayank.infinityschoolhouse.controller;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.service.ContactDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("admin")
@SuppressWarnings("unused")
public class AdminController {
    private ContactDetailService contactDetailService;

    @Autowired
    public AdminController(ContactDetailService contactDetailService) {
        this.contactDetailService = contactDetailService;
    }

    @RequestMapping(value = {"/displayMessages"})
    public ModelAndView displayOpenMessages() {
        ModelAndView modelAndView = new ModelAndView();
        List<ContactDetail> messagesWithOpenStatus = contactDetailService.getAllMessagesWithOpenStatus();
        modelAndView.addObject("openMessages", messagesWithOpenStatus);
        modelAndView.setViewName("messages.html");
        return modelAndView;
    }

    @GetMapping(value = {"/closeMsg"})
    public String closeMessage(@RequestParam("id") int id) {
        boolean isUpdated = contactDetailService.updateMessageStatus(id);
        if (!isUpdated) log.error("Failed to close message with id: {}", id);
        return "redirect:/admin/displayMessages";
    }
}
