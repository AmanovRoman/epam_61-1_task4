package com.epam.spring.hometask.dao.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.information.CommonInformation;
import com.epam.spring.hometask.dao.CommonInfoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcCommonInfoDaoImpl implements CommonInfoDao {
    private DbConnector connector;

    @Autowired
    public JdbcCommonInfoDaoImpl(DbConnector connector) {
        this.connector = connector;
    }

    @Override
    public CommonInformation getByEvent(int eventId) {
        String sql = "SELECT * FROM common_information WHERE event_id = ?";
        try {
            return connector.getConnection().
                    queryForObject(sql, new Object[]{eventId}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public boolean update(CommonInformation info) {
        String sql = "UPDATE common_information SET event_id=?, accessed_by_name_counter=?, price_queried_counter=?, tickets_booked_counter=? WHERE id=?";
        return connector.getConnection().update(sql,
                info.getEventId(),
                info.getAccessedByNameCounter(),
                info.getPriceQueriedCounter(),
                info.getTicketsBookedCounter(),
                info.getId()) > 0;
    }

    @Override
    public int save(CommonInformation info) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("event_id", info.getEventId());
        parameters.put("accessed_by_name_counter", info.getAccessedByNameCounter());
        parameters.put("price_queried_counter", info.getPriceQueriedCounter());
        parameters.put("tickets_booked_counter", info.getTicketsBookedCounter());
        return connector.getNewSimpleJdbcInsert().
                withTableName("common_information").
                usingGeneratedKeyColumns("id").
                executeAndReturnKey(parameters).
                intValue();
    }

    @Override
    public CommonInformation remove(int id) {
        return null;
    }

    @Override
    public CommonInformation getById(int id) {
        String sql = "SELECT * FROM common_information WHERE id = ?";

        try {
            return connector.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<CommonInformation> getAll() {
        String sql = "SELECT * FROM common_information";
        try {
            return connector.getConnection().query(sql, new JdbcCommonInfoDaoImpl.Mapper());
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
