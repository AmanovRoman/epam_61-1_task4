package com.epam.spring.hometask.service.domain.jdbc;

import com.epam.spring.hometask.data.DbConnector;
import com.epam.spring.hometask.domain.information.DiscountInformation;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcDiscountInfoDaoImpl extends DbConnector implements DiscountInfoDao {

    @Autowired
    public JdbcDiscountInfoDaoImpl(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public int save(DiscountInformation info) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("user_id", info.getUserId());
        parameters.put("discount_name", info.getStrategyName());
        parameters.put("counter", info.getUserDiscountCounter());
        return getNewSimpleJdbcInsert().
                withTableName("discount_information").
                usingGeneratedKeyColumns("id").
                executeAndReturnKey(parameters).
                intValue();
    }

    @Override
    public DiscountInformation remove(int id) {
        return null;
    }

    @Override
    public DiscountInformation getById(int id) {
        String sql = "SELECT * FROM discount_information WHERE id = ?";

        try {
            return this.getConnection().queryForObject(sql, new Object[]{id}, new Mapper());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<DiscountInformation> getAll() {
        String sql = "SELECT * FROM discount_information";
        try {
            return this.getConnection().query(sql, new Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<DiscountInformation> getByUserId(Integer userId) {
        String sql = "SELECT * FROM discount_information WHERE user_id = ?";
        try {
            return this.getConnection().query(sql, new Object[]{userId}, new Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public List<DiscountInformation> getByDiscountName(String name) {
        String sql = "SELECT * FROM discount_information WHERE discount_name LIKE ?";
        try {
            return getConnection().query(sql, new Object[]{name}, new Mapper());
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    @Override
    public boolean update(DiscountInformation info) {
        String sql = "UPDATE discount_information SET user_id=?, discount_name=?, counter=? WHERE id=?";
        return getConnection().update(
                sql,
                info.getUserId(),
                info.getStrategyName(),
                info.getUserDiscountCounter(),
                info.getId()) > 0;
    }

    @Override
    public List<Object[]> getDiscountNames() {
        String sql = "SELECT discount_name, SUM(counter) as total FROM spring_course.discount_information group by discount_name order by discount_name";
        try {
            return getConnection().query(sql, new RowMapper<Object[]>() {
                @Override
                public Object[] mapRow(ResultSet resultSet, int i) throws SQLException {
                    Object [] o = new Object[2];
                    o[0] = resultSet.getString("discount_name");
                    o[1] = resultSet.getInt("total");
                    return o;
                }
            });
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    private class Mapper implements RowMapper<DiscountInformation> {
        public DiscountInformation mapRow(ResultSet resultSet, int i) throws SQLException {
            DiscountInformation discountInfo = new DiscountInformation();
            discountInfo.setId(resultSet.getInt("id"));
            discountInfo.setUserId(resultSet.getInt("user_id"));
            discountInfo.setUserDiscountCounter(resultSet.getInt("counter"));
            discountInfo.setStrategyName(resultSet.getString("discount_name"));
            return discountInfo;
        }
    }
}
