package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

import java.util.List;
import java.util.Optional;

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
     * @param contactDetail ContactDetail object to be saved
     * @return true if the contact detail is saved successfully, false otherwise
     */
    @Transactional
    public boolean saveContactDetail(ContactDetail contactDetail) {
        boolean isSaved = false;
        contactDetail.setStatus(ISHConstants.OPEN);
        ContactDetail saved = contactRepository.save(contactDetail);
        if (saved != null && saved.getContactId() > 0) {
            log.info("Contact detail saved successfully: {}", contactDetail);
            isSaved = true;
        } else log.error("Failed to save contact detail: {}", contactDetail);
        return isSaved;
    }

    /**
     * @return List of all messages with status as 'Open'
     */
    public List<ContactDetail> getAllMessagesWithOpenStatus() {
        return contactRepository.findByStatus(ISHConstants.OPEN);
    }

    /**
     * @param contact_id ID of the message to be closed
     * @return true if the message status is updated successfully, false otherwise
     */
    @Transactional
    public boolean updateMessageStatus(int contact_id) {
        boolean isUpdated = false;
        try {
            Optional<ContactDetail> optionalContact = contactRepository.findById(contact_id);
            if (optionalContact.isEmpty()) {
                log.error("Message with id: {} not found", contact_id);
            } else {
                ContactDetail contactDetail = optionalContact.get();
                contactDetail.setStatus(ISHConstants.CLOSE);
                ContactDetail updated = contactRepository.save(contactDetail);
                if (updated != null && updated.getStatus().equals(ISHConstants.CLOSE)) {
                    log.info("Message with id: {} closed successfully", contact_id);
                    isUpdated = true;
                }
            }
        } catch (Exception e) {
            log.error("Error updating message status for id: {}", contact_id, e);
            throw e; // Re-throw the exception to ensure the transaction is rolled back
        }
        return isUpdated;
    }
}