package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@SessionScope
public class ContactDetailService {
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
        log.info("Saving contact detail: {}", contactDetail);
        boolean isSaved = false;
        contactDetail.setStatus(ISHConstants.OPEN);
        contactDetail.setCreatedBy(ISHConstants.ANONYMOUS);
        contactDetail.setCreatedAt(LocalDateTime.now());
        int rowsAffected = contactRepository.saveContactMsg(contactDetail);

        if (rowsAffected > 0) {
            log.info("Contact detail saved successfully: {}", contactDetail);
            isSaved = true;
        } else log.error("Failed to save contact detail: {}", contactDetail);

        return isSaved;
    }

    public List<ContactDetail> getAllMessagesWithOpenStatus() {
        log.info("Fetching all messages with status: {}", ISHConstants.OPEN);
        return contactRepository.getAllMessagesWithStatus(ISHConstants.OPEN);
    }

    public boolean updateMessageStatus(int contact_id, String updated_by) {
        log.info("Closing message with id: {}", contact_id);
        boolean isUpdated = false;
        int rowsAffected = contactRepository.updateMessageStatus(contact_id, ISHConstants.CLOSE, updated_by);

        if (rowsAffected > 0) {
            log.info("Message with id: {} closed successfully", contact_id);
            isUpdated = true;
        } else log.error("Failed to close message with id: {}", contact_id);

        return isUpdated;
    }
}
