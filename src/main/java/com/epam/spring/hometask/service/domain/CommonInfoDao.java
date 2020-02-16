package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.information.CommonInformation;

public interface CommonInfoDao extends AbstractDomainObjectDao<CommonInformation> {
    CommonInformation getByEvent(int var1);

    boolean update(CommonInformation var1);
}
