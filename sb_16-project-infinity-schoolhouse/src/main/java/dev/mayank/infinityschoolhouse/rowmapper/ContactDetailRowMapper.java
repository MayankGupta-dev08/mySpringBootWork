package dev.mayank.infinityschoolhouse.rowmapper;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.util.ContactMsgTableConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ContactDetailRowMapper implements RowMapper<ContactDetail> {
    @Override
    public ContactDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
        ContactDetail contactDetail = new ContactDetail();
        contactDetail.setContactId(rs.getInt(ContactMsgTableConstants.COLUMN_ID));
        contactDetail.setName(rs.getString(ContactMsgTableConstants.COLUMN_NAME));
        contactDetail.setEmail(rs.getString(ContactMsgTableConstants.COLUMN_EMAIL));
        contactDetail.setMobileNum(rs.getString(ContactMsgTableConstants.COLUMN_MOBILE));
        contactDetail.setSubject(rs.getString(ContactMsgTableConstants.COLUMN_SUBJECT));
        contactDetail.setMessage(rs.getString(ContactMsgTableConstants.COLUMN_MESSAGE));
        contactDetail.setStatus(rs.getString(ContactMsgTableConstants.COLUMN_STATUS));
        contactDetail.setCreatedAt(rs.getTimestamp(
                ContactMsgTableConstants.COLUMN_CREATED_AT).toLocalDateTime());
        contactDetail.setCreatedBy(rs.getString(ContactMsgTableConstants.COLUMN_CREATED_BY));

        Timestamp updated_at = rs.getTimestamp(ContactMsgTableConstants.COLUMN_UPDATED_AT);
        if (null != updated_at) contactDetail.setUpdatedAt(updated_at.toLocalDateTime());

        String updated_by = rs.getString(ContactMsgTableConstants.COLUMN_UPDATED_BY);
        if (null != updated_by) contactDetail.setUpdatedBy(updated_by);

        return contactDetail;
    }
}