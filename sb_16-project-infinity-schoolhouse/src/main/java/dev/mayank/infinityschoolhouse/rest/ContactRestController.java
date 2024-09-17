package dev.mayank.infinityschoolhouse.rest;


import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.model.Response;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@SuppressWarnings("unused")
@RequestMapping("/api/contact")
public class ContactRestController {

    private final ContactRepository contactRepository;

    @Autowired
    public ContactRestController(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @GetMapping("/getMessagesByStatus")
    public List<ContactDetail> getMessagesByStatus(@RequestParam(name = "status") String status) {
        return contactRepository.findByStatus(status);
    }

    @GetMapping("/getAllMessagesByStatus")
    public List<ContactDetail> getAllMessagesByStatus(@RequestBody ContactDetail contact) {
        if (contact == null || contact.getStatus() == null) return List.of();

        return contactRepository.findByStatus(contact.getStatus());
    }

    @PostMapping("/saveMessage")
    public ResponseEntity<Response> saveMessage(@RequestHeader(name = "invocationFrom") String invocationFrom,
                                                @Valid @RequestBody ContactDetail contact) {
        log.info("Header invocationFrom = {}", invocationFrom);
        contactRepository.save(contact);
        Response response = new Response("200", "Message saved successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .header("isMessageSaved", "true")
                .body(response);
    }
}
