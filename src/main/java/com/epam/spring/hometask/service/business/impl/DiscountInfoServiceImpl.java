package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.information.DiscountInformation;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountInfoServiceImpl implements DiscountInfoServiceDao {
    @Autowired
    DiscountInfoDao discountInfo;

    @Override
    public int saveInfo(DiscountInformation info) {
        return discountInfo.save(info);
    }

    @Override
    public List<DiscountInformation> findByUserId(int userId) {
        return discountInfo.getByUserId(userId);
    }

    @Override
    public List<DiscountInformation> findByStrategyName(String name) {
        return discountInfo.getByDiscountName(name);
    }

    @Override
    public int increaseCounter(DiscountInformation info) {
        info.setUserDiscountCounter(info.getUserDiscountCounter()+1);
        discountInfo.update(info);
        return info.getUserDiscountCounter();
    }

    @Override
    public String getTextInfo() {
        return null;
    }
}
