package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.information.CommonInformation;

public interface CommonInfoDao extends AbstractDomainObjectDao<CommonInformation> {
    CommonInformation getByEvent(int eventId);

    boolean update(CommonInformation info);
}
