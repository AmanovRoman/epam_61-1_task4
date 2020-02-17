package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.service.domain.DataCleanerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcDataCleanerImpl extends DbConnector implements DataCleanerDao {

    @Autowired
    public JdbcDataCleanerImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void cleanAllData() {
        cleanAllData(new ArrayList<>(
                Arrays.asList(
                        "auditorium",
                        "common_information",
                        "discount_information",
                        "event",
                        "scheduled_events",
                        "ticket",
                        "users"
                )));
    }

    @Override
    public void cleanAllData(List<String> tables) {
        tables.forEach(this::cleanDataTable);
    }

    @Override
    public boolean cleanDataTable(String tableName) {
        getConnection().update("DELETE FROM "+tableName);
        getConnection().execute("ALTER TABLE "+tableName+" AUTO_INCREMENT = 1");
        return true;
    }
}
