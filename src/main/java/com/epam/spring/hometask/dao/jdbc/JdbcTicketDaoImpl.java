package com.epam.spring.hometask.dao.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.dao.TicketDao;
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
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class JdbcTicketDaoImpl implements TicketDao {

    private DbConnector connector;

    @Autowired
    public JdbcTicketDaoImpl(DbConnector connector) {
        this.connector = connector;
    }

    @Override
    public int save(Ticket ticket) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("scheduled_id", ticket.getScheduledEventId());
        parameters.put("user_id", ticket.getUserId());
        parameters.put("seat", ticket.getSeat());
        parameters.put("price", ticket.getPrice());
        parameters.put("discount", ticket.getDiscount());
        parameters.put("discount_value", ticket.getDiscountValue());
        return connector.getNewSimpleJdbcInsert().
                withTableName("ticket").
                usingGeneratedKeyColumns("ticket_id").
                executeAndReturnKey(parameters).
                intValue();
    }

    @Override
    public Ticket remove(int id) {
        return null;
    }

    @Override
    public Ticket getById(int id) {
        String sql = "SELECT * FROM ticket WHERE ticket_id = ?";
        try {
            return connector.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Ticket> getAll() {
        String sql = "SELECT * FROM ticket";
        try {
            return connector.getConnection().query(sql, new Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public void update(Ticket ticket) {
        String sql = "UPDATE ticket SET scheduled_id = ?, user_id = ?, seat = ?, price = ?, discount = ?, discount_value = ? WHERE ticket_id=?";
        connector.getConnection().update(sql,
                ticket.getScheduledEventId(),
                ticket.getUserId(),
                ticket.getSeat(),
                ticket.getPrice(),
                ticket.getDiscount(),
                ticket.getDiscountValue(),
                ticket.getId());
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