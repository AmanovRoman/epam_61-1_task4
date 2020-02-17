package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.enums.EventRating;
import com.epam.spring.hometask.service.domain.EventDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcEventDaoImpl extends DbConnector implements EventDao {
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcEventDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public Event getByName(String name) {
        String sql = "SELECT * FROM event WHERE name LIKE ? LIMIT 1";

        try {
            return getConnection().queryForObject(sql, new Object[]{name}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int save(Event event) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", event.getName());
        parameters.put("base_price", event.getBasePrice());
        parameters.put("rating", event.getRating());
        return this.simpleJdbcInsert.withTableName("event").usingGeneratedKeyColumns("event_id").executeAndReturnKey(parameters).intValue();
    }

    @Override
    public Event remove(int id) {
        return null;
    }

    @Override
    public Event getById(int id) {
        String sql = "SELECT * FROM event WHERE event_id = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Event> getAll() {
        String sql = "SELECT * FROM event";

        try {
            return this.getConnection().query(sql, new Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Mapper implements RowMapper<Event> {
        public Event mapRow(ResultSet resultSet, int i) throws SQLException {
            Event event = new Event();
            event.setId(resultSet.getInt("event_id"));
            event.setName(resultSet.getString("name"));
            event.setBasePrice(resultSet.getDouble("base_price"));
            event.setRating(EventRating.values()[resultSet.getInt("rating") - 1]);
            return event;
        }
    }
}
