package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.information.CommonInformation;
import com.epam.spring.hometask.service.domain.CommonInfoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCommonInfoDaoImpl extends DbConnector implements CommonInfoDao {
    @Autowired
    SimpleJdbcInsert simpleJdbcInsert;

    @Autowired
    public JdbcCommonInfoDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    public CommonInformation getByEvent(int eventId) {
        String sql = "SELECT * FROM common_information WHERE event_id = ?";
        try {
            return getConnection().
                    queryForObject(sql, new Object[]{eventId}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    public boolean update(CommonInformation info) {
        String sql = "UPDATE common_information SET accessed_by_name_counter=?, price_queried_counter=?, tickets_booked_counter=? WHERE id=?";
        return this.getConnection().update(sql, info.getAccessedByNameCounter(), info.getPriceQueriedCounter(), info.getTicketsBookedCounter(), info.getId()) > 0;
    }

    public int save(CommonInformation info) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("event_id", info.getEventId());
        parameters.put("accessed_by_name_counter", info.getAccessedByNameCounter());
        parameters.put("price_queried_counter", info.getPriceQueriedCounter());
        parameters.put("tickets_booked_counter", info.getTicketsBookedCounter());
        return simpleJdbcInsert.
                withTableName("common_information").
                usingGeneratedKeyColumns("id").
                executeAndReturnKey(parameters).
                intValue();
    }

    public CommonInformation remove(int id) {
        return null;
    }

    public CommonInformation getById(int id) {
        String sql = "SELECT * FROM common_information WHERE id = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    public List<CommonInformation> getAll() {
        String sql = "SELECT * FROM common_information";
        try {
            return this.getConnection().query(sql, new JdbcCommonInfoDaoImpl.Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Mapper implements RowMapper<CommonInformation> {

        public CommonInformation mapRow(ResultSet resultSet, int i) throws SQLException {
            CommonInformation commonInfo = new CommonInformation();
            commonInfo.setId(resultSet.getInt("id"));
            commonInfo.setEventId(resultSet.getInt("event_id"));
            commonInfo.setAccessedByNameCounter(resultSet.getInt("accessed_by_name_counter"));
            commonInfo.setPriceQueriedCounter(resultSet.getInt("price_queried_counter"));
            commonInfo.setTicketsBookedCounter(resultSet.getInt("tickets_booked_counter"));
            return commonInfo;
        }
    }
}