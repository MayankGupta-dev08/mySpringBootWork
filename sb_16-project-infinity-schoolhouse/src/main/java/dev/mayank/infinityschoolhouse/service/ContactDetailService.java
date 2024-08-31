package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;

@Service
@SessionScope
public class ContactDetailService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ContactDetailService.class);
    private final ContactRepository contactRepository;

    @Autowired
    public ContactDetailService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    /**
     * @param contactDetail ContactDetail
     * @return true if the contact detail is saved successfully, false otherwise
     */
    public boolean saveContactDetail(ContactDetail contactDetail) {
        LOGGER.info("Saving contact detail: {}", contactDetail);
        boolean isSaved = false;
        contactDetail.setStatus(ISHConstants.OPEN);
        contactDetail.setCreatedBy(ISHConstants.ANONYMOUS);
        contactDetail.setCreatedAt(LocalDateTime.now());
        int rowsAffected = contactRepository.saveContactMsg(contactDetail);

        if (rowsAffected > 0) {
            LOGGER.info("Contact detail saved successfully: {}", contactDetail);
            isSaved = true;
        } else LOGGER.error("Failed to save contact detail: {}", contactDetail);

        return isSaved;
    }
}
