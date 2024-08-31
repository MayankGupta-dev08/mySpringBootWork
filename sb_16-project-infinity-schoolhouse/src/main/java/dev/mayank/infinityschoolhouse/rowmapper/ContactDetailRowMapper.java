package dev.mayank.infinityschoolhouse.rowmapper;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.util.CMTConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContactDetailRowMapper implements RowMapper<ContactDetail> {
    @Override
    public ContactDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setContactId(rs.getInt(CMTConstants.COLUMN_ID));
        contactDetail.setName(rs.getString(CMTConstants.COLUMN_NAME));
        contactDetail.setEmail(rs.getString(CMTConstants.COLUMN_EMAIL));
        contactDetail.setMobileNum(rs.getString(CMTConstants.COLUMN_MOBILE));
        contactDetail.setSubject(rs.getString(CMTConstants.COLUMN_SUBJECT));
        contactDetail.setMessage(rs.getString(CMTConstants.COLUMN_MESSAGE));
        contactDetail.setStatus(rs.getString(CMTConstants.COLUMN_STATUS));
        contactDetail.setCreatedAt(rs.getTimestamp(
                CMTConstants.COLUMN_CREATED_AT).toLocalDateTime());
        contactDetail.setCreatedBy(rs.getString(CMTConstants.COLUMN_CREATED_BY));

        Timestamp updated_at = rs.getTimestamp(CMTConstants.COLUMN_UPDATED_AT);
        if (null != updated_at) contactDetail.setUpdatedAt(updated_at.toLocalDateTime());

        String updated_by = rs.getString(CMTConstants.COLUMN_UPDATED_BY);
        if (null != updated_by) contactDetail.setUpdatedBy(updated_by);

        return contactDetail;
    }
}