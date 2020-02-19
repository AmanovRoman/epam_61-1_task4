package com.epam.spring.hometask.dao.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.dao.AuditoriumDao;
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
public class JdbcAuditoriumDaoImpl implements AuditoriumDao {

    private DbConnector connector;

    @Autowired
    public JdbcAuditoriumDaoImpl(DbConnector connector) {
        this.connector = connector;
    }

    @Override
    public int save(Auditorium auditorium) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("name", auditorium.getName());
        parameters.put("number_of_seats", auditorium.getNumberOfSeats());
        parameters.put("vip_multiplier", auditorium.getVipSeatsMultiplier());
        parameters.put("vip_seats", auditorium.getVipSeats().toString());
        return connector.getNewSimpleJdbcInsert().
                withTableName("auditorium").
                usingGeneratedKeyColumns("auditorium_id").
                executeAndReturnKey(parameters).
                intValue();
    }

    @Override
    public List<Auditorium> getAll() {
        String sql = "SELECT * FROM auditorium";
        try {
            return connector.getConnection().query(sql, new Mapper());
        } catch (Exception e) { return new ArrayList<>(); }
    }

    @Override
    public Auditorium getById(int id) {
        String sql = "SELECT * FROM auditorium WHERE auditorium_id = ?";
        try {
            return connector.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) { return null; }
    }

    private class Mapper implements RowMapper<Auditorium> {
        public Auditorium mapRow(ResultSet resultSet, int i) throws SQLException {
            Auditorium auditorium = new Auditorium();
            auditorium.setId(resultSet.getInt("auditorium_id"));
            auditorium.setName(resultSet.getString("name"));
            auditorium.setNumberOfSeats(resultSet.getInt("number_of_seats"));
            auditorium.setVipSeatsMultiplier(resultSet.getDouble("vip_multiplier"));
            auditorium.setVipSeats(resultSet.getString("vip_seats"));
            return auditorium;
        }
    }
}