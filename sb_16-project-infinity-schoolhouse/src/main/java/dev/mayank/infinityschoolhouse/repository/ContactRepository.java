package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ContactRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public ContactRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int saveContactMsg(ContactDetail contactDetail) {
        String sql = "INSERT INTO contact_msg (name, mobile_num, email, subject, message, status, created_at, created_by) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, contactDetail.getName(), contactDetail.getMobileNum(), contactDetail.getEmail(),
                contactDetail.getSubject(), contactDetail.getMessage(), contactDetail.getStatus(), contactDetail.getCreatedAt(),
                contactDetail.getCreatedBy());
    }
}