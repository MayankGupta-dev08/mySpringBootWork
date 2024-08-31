package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.rowmapper.ContactDetailRowMapper;
import dev.mayank.infinityschoolhouse.util.ContactMsgTableConstants;
import dev.mayank.infinityschoolhouse.util.SQLQueryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(ContactDetail contactDetail) {
        log.info("Saving contact detail: {}", contactDetail);

        String sql = SQLQueryBuilder.buildInsertQuery(ContactMsgTableConstants.TABLE_NAME,
                ContactMsgTableConstants.COLUMN_NAME, ContactMsgTableConstants.COLUMN_MOBILE,
                ContactMsgTableConstants.COLUMN_EMAIL, ContactMsgTableConstants.COLUMN_SUBJECT,
                ContactMsgTableConstants.COLUMN_MESSAGE, ContactMsgTableConstants.COLUMN_STATUS,
                ContactMsgTableConstants.COLUMN_CREATED_AT, ContactMsgTableConstants.COLUMN_CREATED_BY);
        log.info("SQL for insert: {}", sql);

        int update = jdbcTemplate.update(sql, contactDetail.getName(), contactDetail.getMobileNum(), contactDetail.getEmail(),
                contactDetail.getSubject(), contactDetail.getMessage(), contactDetail.getStatus(), contactDetail.getCreatedAt(),
                contactDetail.getCreatedBy());

        log.info("Contact detail saved successfully: {}", contactDetail);
        return update;
    }

    public List<ContactDetail> getAllMessagesWithStatus(String status) {
        log.info("Fetching all messages with status: {}", status);

        String sql = SQLQueryBuilder.buildSelectQueryWithCondition(ContactMsgTableConstants.TABLE_NAME,
                ContactMsgTableConstants.COLUMN_STATUS, ContactMsgTableConstants.COLUMN_ALL);

        List<ContactDetail> list = jdbcTemplate.query(sql, ps -> ps.setString(1, status), new ContactDetailRowMapper());

        log.info("Returning all messages with status: {}", status);
        return list;
    }

    public int updateMessageStatus(int id, String status, String user) {
        log.info("Updating message status with id: {}", id);

        String sql = SQLQueryBuilder.buildUpdateQuery(ContactMsgTableConstants.TABLE_NAME,
                ContactMsgTableConstants.COLUMN_ID, ContactMsgTableConstants.COLUMN_STATUS,
                ContactMsgTableConstants.COLUMN_UPDATED_AT, ContactMsgTableConstants.COLUMN_UPDATED_BY);
        log.info("SQL for update: {}", sql);

        int update = jdbcTemplate.update(sql, pss -> {
            pss.setString(1, status);
            pss.setString(3, user);
            pss.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pss.setInt(4, id);
        });

        log.info("Message status updated successfully with id: {}", id);
        return update;
    }
}