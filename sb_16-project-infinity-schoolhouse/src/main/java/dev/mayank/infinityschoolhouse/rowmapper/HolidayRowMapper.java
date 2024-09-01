package dev.mayank.infinityschoolhouse.rowmapper;

import dev.mayank.infinityschoolhouse.model.Holiday;
import dev.mayank.infinityschoolhouse.util.HTConstants;
import dev.mayank.infinityschoolhouse.util.HolidayType;
import dev.mayank.infinityschoolhouse.util.TableConstants;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class HolidayRowMapper implements RowMapper<Holiday> {
    @Override
    public Holiday mapRow(ResultSet rs, int rowNum) throws SQLException {
        Holiday holiday = new Holiday();
        holiday.setId(rs.getInt(HTConstants.COLUMN_ID));
        holiday.setName(rs.getString(HTConstants.COLUMN_NAME));
        holiday.setDate(rs.getDate(HTConstants.COLUMN_DATE).toLocalDate());
        holiday.setType(HolidayType.valueOf(rs.getString(HTConstants.COLUMN_TYPE).toUpperCase()));
        holiday.setCreatedAt(rs.getTimestamp(TableConstants.COLUMN_CREATED_AT).toLocalDateTime());
        holiday.setCreatedBy(rs.getString(TableConstants.COLUMN_CREATED_BY));

        Timestamp updatedAt = rs.getTimestamp(TableConstants.COLUMN_UPDATED_AT);
        if (updatedAt != null) holiday.setUpdatedAt(updatedAt.toLocalDateTime());
        String updatedBY = rs.getString(TableConstants.COLUMN_UPDATED_BY);
        if (updatedBY != null) holiday.setUpdatedBy(updatedBY);
        return holiday;
    }
}