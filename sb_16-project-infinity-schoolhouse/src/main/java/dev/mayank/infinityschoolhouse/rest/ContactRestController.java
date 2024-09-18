package dev.mayank.infinityschoolhouse.rest;


import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.model.Response;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
@SuppressWarnings("unused")
@RequestMapping(path = "/api/contact", produces = {"application/json", "application/xml"})
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

    @DeleteMapping("/deleteMessage")
    public ResponseEntity<Response> deleteMessage(RequestEntity<ContactDetail> requestEntity) {
        requestEntity.getHeaders().forEach((key, value) -> log.info("Header: {} = {}", key, value));
        ContactDetail contactMsg = requestEntity.getBody();
        if (contactMsg == null) return ResponseEntity.badRequest().build();

        Optional<ContactDetail> optional = contactRepository.findById(contactMsg.getContactId());
        if (optional.isEmpty()) {
            Response response = new Response("404", "Message not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        contactRepository.deleteById(contactMsg.getContactId());
        Response response = new Response("200", "Message deleted successfully");
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/closeMessage")
    public ResponseEntity<Response> closeMessage(@RequestBody ContactDetail contact) {
        Optional<ContactDetail> contactMsgOpt = contactRepository.findById(contact.getContactId());
        if (contactMsgOpt.isEmpty()) {
            Response response = new Response("404", "Message not found");
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .header("isMessageClosed", "false")
                    .body(response);
        } else if (contactMsgOpt.get().getStatus().equalsIgnoreCase(ISHConstants.CLOSED)) {
            Response response = new Response("200", "Message already closed");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("isMessageClosed", "false")
                    .body(response);
        } else {
            contactMsgOpt.get().setStatus(ISHConstants.CLOSED);
            contactRepository.save(contactMsgOpt.get());
            Response response = new Response("200", "Message closed successfully");
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .header("isMessageClosed", "true")
                    .body(response);
        }
    }
}