package dev.mayank.ishproxyservice.controller;

import dev.mayank.ishproxyservice.model.ContactDetail;
import dev.mayank.ishproxyservice.model.Response;
import dev.mayank.ishproxyservice.proxy.ContactMsgProxy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RestController
@SuppressWarnings("unused")
public class ContactProxyRestController {
    private final ContactMsgProxy contactMsgProxy;
    private final RestTemplate restTemplate;
    private final WebClient webClient;

    @Autowired
    public ContactProxyRestController(ContactMsgProxy contactMsgProxy, RestTemplate restTemplate, WebClient webClient) {
        this.contactMsgProxy = contactMsgProxy;
        this.restTemplate = restTemplate;
        this.webClient = webClient;
    }

    @GetMapping("/getMessages")
    public List<ContactDetail> getMessages(@RequestParam("status") String status) {
        log.info("Fetching messages from contact-msg-service");
        return contactMsgProxy.getMessagesByStatus(status);
    }

    @PostMapping("/sendMessage")
    public ResponseEntity<Response> sendMessage(@RequestBody ContactDetail message) {
        log.info("Sending message using restTemplate");
        String uri = "http://localhost:8080/api/contact/saveMessage";
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("invocationFrom", "restTemplate");
        HttpEntity<ContactDetail> httpEntity = new HttpEntity<>(message, httpHeaders);
        ResponseEntity<Response> responseEntity = restTemplate.exchange(
                uri, HttpMethod.POST, httpEntity, Response.class);
        return responseEntity;
    }

    @PostMapping("/saveMsg")
    public Mono<Response> saveMsg(@RequestBody ContactDetail message) {
        log.info("Saving message using webClient");
        String uri = "http://localhost:8080/api/contact/saveMessage";
        return webClient.post().uri(uri)
                .header("Content-Type", "application/json")
                .header("invocationFrom", "webClient")
                .body(Mono.just(message), ContactDetail.class)
                .retrieve()
                .bodyToMono(Response.class);
    }
}