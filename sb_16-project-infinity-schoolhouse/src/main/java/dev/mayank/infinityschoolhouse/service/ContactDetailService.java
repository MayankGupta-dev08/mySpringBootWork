package dev.mayank.infinityschoolhouse.service;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.repository.ContactRepository;
import dev.mayank.infinityschoolhouse.util.ISHConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.SessionScope;

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
    public Page<ContactDetail> getOpenMessagesWithPaging(int pageNum, String sortField, String sortDir) {
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending());
        Page<ContactDetail> msgPage = contactRepository.findByStatus(ISHConstants.OPEN, pageable);
        log.info("Retrieved all msgPage with status as 'Open'");
        return msgPage;
    }

    /**
     * @param contact_id ID of the message to be closed
     * @return true if the message status is updated successfully, false otherwise
     */
    @Transactional
    public boolean updateMessageStatus(int contact_id) {
        int rows = contactRepository.updateStatusById(contact_id, ISHConstants.CLOSED);
        return rows > 0;
    }
}