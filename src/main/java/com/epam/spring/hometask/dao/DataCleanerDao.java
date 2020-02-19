package com.epam.spring.hometask.dao;

import java.util.List;

public interface DataCleanerDao {
    void cleanAllData();

    void cleanAllData(List<String> tables);

    boolean cleanDataTable(String tableName);
}
