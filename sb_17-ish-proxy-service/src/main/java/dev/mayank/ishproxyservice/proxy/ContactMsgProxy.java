package dev.mayank.ishproxyservice.proxy;

import dev.mayank.ishproxyservice.config.AppConfig;
import dev.mayank.ishproxyservice.model.ContactDetail;
import feign.Headers;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "contact-msg-service",
        url = "http://localhost:8080/api/contact",
        configuration = AppConfig.class
)
@SuppressWarnings("unused")
public interface ContactMsgProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/getMessagesByStatus")
    @Headers(value = "Content-Type: application/json")
    List<ContactDetail> getMessagesByStatus(@RequestParam("status") String status);
}