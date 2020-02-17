package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.information.DiscountInformation;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import com.epam.spring.hometask.service.business.UserServiceDao;
import com.epam.spring.hometask.service.domain.DiscountInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DiscountInfoServiceImpl implements DiscountInfoServiceDao {
    @Autowired
    DiscountInfoDao discountInfo;
    @Autowired
    UserServiceDao userService;

    @Override
    public int saveInfo(DiscountInformation info) {
        return discountInfo.save(info);
    }

    @Override
    public List<DiscountInformation> findByUserId(Integer userId) {
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
        StringBuilder text = new StringBuilder();
        discountInfo.getDiscountNames().forEach(discount -> {
            text.append(discount[0]).append(" (total ").append(discount[1]).append(")").
                    append("\n");
            for (DiscountInformation discountInformation : discountInfo.getByDiscountName((String) discount[0])) {
                User user = userService.getUserById(discountInformation.getUserId());
                text.append("\t").
                        append(user == null ? "Not registered user" : user.getFirstName()).
                        append(" - ").
                        append(discountInformation.getUserDiscountCounter()).append("\n");
            }
        });
        return text.toString();
    }

}
