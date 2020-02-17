package com.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan({"com.epam.spring.hometask"})
@PropertySource({"classpath:database.properties"})
@EnableAspectJAutoProxy
public class CommonConfiguration {
    @Autowired
    Environment environment;

    public CommonConfiguration() {
    }

    @Bean
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(this.environment.getRequiredProperty("database.driver"));
        dataSource.setUrl(this.environment.getRequiredProperty("database.url"));
        dataSource.setUsername(this.environment.getRequiredProperty("database.user"));
        dataSource.setPassword(this.environment.getRequiredProperty("database.password"));
        return dataSource;
    }

    @Bean
    @Scope("prototype")
    SimpleJdbcInsert simpleJdbcInsert(@Autowired DataSource dataSource) {
        return new SimpleJdbcInsert(dataSource);
    }
}
