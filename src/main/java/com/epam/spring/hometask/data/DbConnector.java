package com.epam.spring.hometask.data;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;

public class DbConnector {
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert simpleInsert;

    protected JdbcTemplate getConnection() {
        return this.jdbcTemplate;
    }

    public DbConnector(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
