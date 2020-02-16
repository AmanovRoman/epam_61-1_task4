package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.service.domain.ScheduleEventDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcScheduleEventDaoImpl extends DbConnector implements ScheduleEventDao {
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcScheduleEventDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public int save(ScheduledEvents scheduled) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("event_id", scheduled.getEventId());
        parameters.put("auditorium_id", scheduled.getAuditoriumId());
        parameters.put("event_time", Timestamp.valueOf(scheduled.getEventTime()));
        parameters.put("price_multiplier", scheduled.getTicketPriceMultiplier());
        return simpleJdbcInsert.
                withTableName("scheduled_events").
                usingGeneratedKeyColumns("scheduled_id").
                executeAndReturnKey(parameters).
                intValue();
    }

    public ScheduledEvents remove(int id) {
        return null;
    }

    public ScheduledEvents getById(int id) {
        String sql = "SELECT * FROM scheduled_events WHERE scheduled_id = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception var4) {
            return null;
        }
    }

    public List<ScheduledEvents> getAll() {
        String sql = "SELECT * FROM scheduled_events";

        try {
            return this.getConnection().query(sql, new Mapper());
        } catch (Exception var3) {
            return new ArrayList<>();
        }
    }

    private class Mapper implements RowMapper<ScheduledEvents> {
        public ScheduledEvents mapRow(ResultSet resultSet, int i) throws SQLException {
            ScheduledEvents scheduled = new ScheduledEvents();
            scheduled.setId(resultSet.getInt("scheduled_id"));
            scheduled.setEventId(resultSet.getInt("event_id"));
            scheduled.setAuditoriumId(resultSet.getInt("auditorium_id"));
            scheduled.setTicketPriceMultiplier((double)resultSet.getInt("price_multiplier"));
            Timestamp dateTime = resultSet.getTimestamp("event_time");
            scheduled.setEventTime(dateTime == null ? null : dateTime.toLocalDateTime());
            return scheduled;
        }
    }
}