package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.domain.UserDao;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class JdbcUserDaoImpl extends DbConnector implements UserDao {
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcUserDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public User getUserByEmail(String email) {
        String sql = "SELECT * FROM users WHERE email = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{email}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(User user) {
        String sql = "UPDATE users SET first_name=?, last_name=?, email=?, birthday=?, user_type=? WHERE user_id=?";
        return getConnection().
                update(sql,
                        user.getFirstName(),
                        user.getLastName(),
                        user.getEmail(),
                        Date.valueOf(user.getBirthDate().plusDays(1L)),
                        user.getUserType(),
                        user.getId()) > 0;
    }

    @Override
    public int save(User user) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("first_name", user.getFirstName());
        parameters.put("last_name", user.getLastName());
        parameters.put("email", user.getEmail());
        parameters.put("birthday", user.getBirthDate());
        parameters.put("user_type", user.getUserType());
        return simpleJdbcInsert.
                withTableName("users").
                usingGeneratedKeyColumns("user_id").
                executeAndReturnKey(parameters).
                intValue();
    }

    @Override
    public User remove(int id) {
        return null;
    }

    @Override
    public User getById(int id) {
        String sql = "SELECT * FROM users WHERE user_id = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "SELECT * FROM users";
        try {
            return this.getConnection().query(sql, new JdbcUserDaoImpl.Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    private class Mapper implements RowMapper<User> {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setId(resultSet.getInt("user_id"));
            user.setFirstName(resultSet.getString("first_name"));
            user.setLastName(resultSet.getString("last_name"));
            user.setEmail(resultSet.getString("email"));
            user.setUserType(resultSet.getInt("user_type"));
            Date date = resultSet.getDate("birthday");
            user.setBirthDate(date == null ? null : date.toLocalDate());
            return user;
        }
    }
}