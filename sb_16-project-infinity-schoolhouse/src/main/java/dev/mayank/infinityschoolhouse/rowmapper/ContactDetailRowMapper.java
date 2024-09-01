package dev.mayank.infinityschoolhouse.rowmapper;

import dev.mayank.infinityschoolhouse.model.ContactDetail;
import dev.mayank.infinityschoolhouse.util.CMTConstants;
import dev.mayank.infinityschoolhouse.util.TableConstants;
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
        contactDetail.setCreatedAt(rs.getTimestamp(TableConstants.COLUMN_CREATED_AT).toLocalDateTime());
        contactDetail.setCreatedBy(rs.getString(TableConstants.COLUMN_CREATED_BY));

        Timestamp updatedAt = rs.getTimestamp(TableConstants.COLUMN_UPDATED_AT);
        if (null != updatedAt) contactDetail.setUpdatedAt(updatedAt.toLocalDateTime());
        String updatedBy = rs.getString(TableConstants.COLUMN_UPDATED_BY);
        if (null != updatedBy) contactDetail.setUpdatedBy(updatedBy);
        return contactDetail;
    }
}