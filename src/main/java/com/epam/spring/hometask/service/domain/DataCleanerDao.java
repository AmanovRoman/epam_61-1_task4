package com.epam.spring.hometask.service.domain;

import java.util.List;

public interface DataCleanerDao {
    void cleanAllData();

    void cleanAllData(List<String> tables);

    boolean cleanDataTable(String tableName);
}
