package com.epam.spring.hometask.dao.jdbc;

import com.epam.spring.hometask.dao.DataCleanerDao;
import com.epam.spring.hometask.data.DbConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class JdbcDataCleanerImpl implements DataCleanerDao {
    private DbConnector connector;

    @Autowired
    public JdbcDataCleanerImpl(DbConnector connector) {
        this.connector = connector;
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
        connector.getConnection().update("DELETE FROM " + tableName);
        connector.getConnection().execute("ALTER TABLE " + tableName + " AUTO_INCREMENT = 1");
        return true;
    }
}
