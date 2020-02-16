package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.information.DiscountInformation;
import java.util.List;

public interface DiscountInfoDao extends AbstractDomainObjectDao<DiscountInformation> {
    List<DiscountInformation> getByUserId(int var1);

    List<DiscountInformation> getByDiscountName(String var1);

    boolean update(DiscountInformation info);
}
