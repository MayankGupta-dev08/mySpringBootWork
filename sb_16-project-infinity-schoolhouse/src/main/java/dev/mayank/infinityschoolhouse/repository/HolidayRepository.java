package dev.mayank.infinityschoolhouse.repository;

import dev.mayank.infinityschoolhouse.model.Holiday;
import dev.mayank.infinityschoolhouse.rowmapper.HolidayRowMapper;
import dev.mayank.infinityschoolhouse.util.HTConstants;
import dev.mayank.infinityschoolhouse.util.SQLQueryBuilder;
import dev.mayank.infinityschoolhouse.util.TableConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
public class HolidayRepository {
    private final JdbcTemplate jdbcTemplate;

    public HolidayRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Holiday> getAllHolidays() {
        String sql = SQLQueryBuilder.buildSelectQuery(HTConstants.TABLE_NAME, TableConstants.COLUMN_ALL);
        log.debug("SQL for select: {}", sql);
        return jdbcTemplate.query(sql, new HolidayRowMapper());
    }
}
