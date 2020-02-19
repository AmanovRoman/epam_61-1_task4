package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.information.DiscountInformation;

import java.util.List;

public interface DiscountInfoDao extends AbstractDomainObjectDao<DiscountInformation> {
    List<DiscountInformation> getByUserId(Integer userId);

    List<DiscountInformation> getByDiscountName(String discount);

    boolean update(DiscountInformation info);

    List<Object[]> getDiscountNames();
}
