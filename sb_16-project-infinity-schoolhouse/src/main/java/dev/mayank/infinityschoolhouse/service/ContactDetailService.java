package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class ContactDetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDetailService.class);
    private int counter = 0;

    public ContactDetailService() {
        LOGGER.info("ContactDetailService instance created. Counter: {}", counter);
    }

    /**
     * @param contactDetail ContactDetail
     * @return true if the contact detail is saved successfully, false otherwise
     */
    public boolean saveContactDetail(ContactDetail contactDetail) {
        boolean isSaved = false;
        LOGGER.info("Contact Detail: " + contactDetail.toString());
        // TODO: Save the contact detail to the database
        return !isSaved;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }
}
