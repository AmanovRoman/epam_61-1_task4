package com.epam.spring.hometask.data;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

@Component
public class DbConnector {

    @Autowired
    ApplicationContext context;

    private JdbcTemplate jdbcTemplate;

    protected JdbcTemplate getConnection() {
        return this.jdbcTemplate;
    }

    @Autowired
    public DbConnector(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    protected SimpleJdbcInsert getNewSimpleJdbcInsert() {
        return  (SimpleJdbcInsert) context.getBean("simpleJdbcInsert");
    }

}
