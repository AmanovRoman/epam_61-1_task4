package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.domain.TicketDao;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class JdbcTicketDaoImpl extends DbConnector implements TicketDao {
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcTicketDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public int save(Ticket ticket) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("scheduled_id", ticket.getScheduledEventId());
        parameters.put("user_id", ticket.getUserId());
        parameters.put("seat", ticket.getSeat());
        parameters.put("price", ticket.getPrice());
        parameters.put("discount", ticket.getDiscount());
        parameters.put("discount_value", ticket.getDiscountValue());
        return simpleJdbcInsert.
                withTableName("ticket").
                usingGeneratedKeyColumns("ticket_id").
                executeAndReturnKey(parameters).
                intValue();
    }

    public Ticket remove(int id) {
        return null;
    }

    public Ticket getById(int id) {
        String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception var4) {
            return null;
        }
    }

    public List<Ticket> getAll() {
        String sql = "SELECT * FROM ticket";
        try {
            return this.getConnection().query(sql, new Mapper());
        } catch (Exception var3) {
            return new ArrayList<>();
        }
    }

    private class Mapper implements RowMapper<Ticket> {
        public Ticket mapRow(ResultSet resultSet, int i) throws SQLException {
            Ticket ticket = new Ticket();
            ticket.setId(resultSet.getInt("ticket_id"));
            ticket.setScheduledEventId(resultSet.getInt("scheduled_id"));
            ticket.setUserId(resultSet.getInt("user_id"));
            ticket.setPrice(resultSet.getDouble("price"));
            ticket.setSeat(resultSet.getInt("seat"));
            ticket.setDiscountValue(resultSet.getDouble("seat"));
            ticket.setDiscount(resultSet.getString("discount"));
            return ticket;
        }
    }
}