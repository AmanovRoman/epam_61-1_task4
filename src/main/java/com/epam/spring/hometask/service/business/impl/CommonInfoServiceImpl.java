package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.information.CommonInformation;
import com.epam.spring.hometask.service.business.CommonInfoServiceDao;
import com.epam.spring.hometask.service.domain.CommonInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class CommonInfoServiceImpl implements CommonInfoServiceDao {
    @Autowired
    CommonInfoDao commonInfo;

    public int saveInfo(CommonInformation info) {
        return this.commonInfo.save(info);
    }

    public CommonInformation findByEventId(int eventId) {
        return commonInfo.getByEvent(eventId);
    }

    public int increaseAccessedByName(CommonInformation info) {
        info.setAccessedByNameCounter(info.getAccessedByNameCounter() + 1);
        commonInfo.update(info);
        return info.getAccessedByNameCounter();
    }

    public int increasePriceQueried(CommonInformation info) {
        info.setPriceQueriedCounter(info.getPriceQueriedCounter() + 1);
        commonInfo.update(info);
        return info.getPriceQueriedCounter();
    }

    public int increaseTicketBooked(CommonInformation info) {
        info.setTicketsBookedCounter(info.getTicketsBookedCounter() + 1);
        commonInfo.update(info);
        return info.getTicketsBookedCounter();
    }

    public List<CommonInformation> getAllCommonInformation() {
        return commonInfo.getAll();
    }

    public String getTextInfo(List<CommonInformation> list) {
        return null;
    }
}