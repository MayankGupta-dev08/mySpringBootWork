package dev.mayank.ishproxyservice.controller;

import dev.mayank.ishproxyservice.model.ContactDetail;
import dev.mayank.ishproxyservice.proxy.ContactMsgProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@SuppressWarnings("unused")
public class ContactProxyRestController {
    private final ContactMsgProxy contactMsgProxy;

    @Autowired
    public ContactProxyRestController(ContactMsgProxy contactMsgProxy) {
        this.contactMsgProxy = contactMsgProxy;
    }

    @GetMapping("/getMessages")
    public List<ContactDetail> getMessages(@RequestParam("status") String status) {
        log.info("Fetching messages from contact-msg-service");
        return contactMsgProxy.getMessagesByStatus(status);
    }
}
