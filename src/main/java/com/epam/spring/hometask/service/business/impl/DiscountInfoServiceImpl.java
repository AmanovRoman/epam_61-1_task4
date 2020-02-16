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

    public int saveInfo(DiscountInformation info) {
        return discountInfo.save(info);
    }

    public List<DiscountInformation> findByUserId(int userId) {
        return discountInfo.getByUserId(userId);
    }

    public List<DiscountInformation> findByStrategyName(String name) {
        return discountInfo.getByDiscountName(name);
    }

    public int increaseCounter(DiscountInformation info) {
        info.setUserDiscountCounter(info.getUserDiscountCounter()+1);
        discountInfo.update(info);
        return info.getUserDiscountCounter();
    }

    public String getTextInfo(List<DiscountInformation> list) {
        return null;
    }
}
