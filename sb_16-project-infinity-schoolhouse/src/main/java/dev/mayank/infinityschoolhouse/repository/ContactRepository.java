package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.rowmapper.ContactDetailRowMapper;
import dev.mayank.infinityschoolhouse.util.CMTConstants;
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
        String sql = SQLQueryBuilder.buildInsertQuery(CMTConstants.TABLE_NAME,
                CMTConstants.COLUMN_NAME, CMTConstants.COLUMN_MOBILE,
                CMTConstants.COLUMN_EMAIL, CMTConstants.COLUMN_SUBJECT,
                CMTConstants.COLUMN_MESSAGE, CMTConstants.COLUMN_STATUS,
                CMTConstants.COLUMN_CREATED_AT, CMTConstants.COLUMN_CREATED_BY);
        log.debug("SQL for insert: {}", sql);
        return jdbcTemplate.update(sql, contactDetail.getName(), contactDetail.getMobileNum(), contactDetail.getEmail(),
                contactDetail.getSubject(), contactDetail.getMessage(), contactDetail.getStatus(), contactDetail.getCreatedAt(),
                contactDetail.getCreatedBy());
    }

    public List<ContactDetail> getAllMessagesWithStatus(String status) {
        String sql = SQLQueryBuilder.buildSelectQueryWithCondition(CMTConstants.TABLE_NAME,
                CMTConstants.COLUMN_STATUS, CMTConstants.COLUMN_ALL);
        log.debug("SQL for select: {}", sql);
        return jdbcTemplate.query(sql, ps -> ps.setString(1, status), new ContactDetailRowMapper());
    }

    public int updateMessageStatus(int id, String status, String user) {
        String sql = SQLQueryBuilder.buildUpdateQuery(CMTConstants.TABLE_NAME,
                CMTConstants.COLUMN_ID, CMTConstants.COLUMN_STATUS,
                CMTConstants.COLUMN_UPDATED_AT, CMTConstants.COLUMN_UPDATED_BY);
        log.debug("SQL for update: {}", sql);
        return jdbcTemplate.update(sql, pss -> {
            pss.setString(1, status);
            pss.setString(3, user);
            pss.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            pss.setInt(4, id);
        });
    }
}