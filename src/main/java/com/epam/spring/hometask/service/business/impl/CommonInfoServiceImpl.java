package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.information.CommonInformation;
import com.epam.spring.hometask.service.business.CommonInfoServiceDao;
import com.epam.spring.hometask.service.business.EventServiceDao;
import com.epam.spring.hometask.service.domain.CommonInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
@Primary
public class CommonInfoServiceImpl implements CommonInfoServiceDao {
    @Autowired
    CommonInfoDao commonInfo;

    @Autowired
    EventServiceDao eventService;

    @Override
    public int saveInfo(CommonInformation info) {
        return this.commonInfo.save(info);
    }

    @Override
    public CommonInformation findByEventId(int eventId) {
        return commonInfo.getByEvent(eventId);
    }

    @Override
    public int increaseAccessedByName(CommonInformation info) {
        int counter = info.getAccessedByNameCounter() + 1;
        info.setAccessedByNameCounter(counter);
        commonInfo.update(info);
        return info.getAccessedByNameCounter();
    }

    @Override
    public int increasePriceQueried(CommonInformation info) {
        info.setPriceQueriedCounter(info.getPriceQueriedCounter() + 1);
        commonInfo.update(info);
        return info.getPriceQueriedCounter();
    }

    @Override
    public int increaseTicketBooked(CommonInformation info) {
        info.setTicketsBookedCounter(info.getTicketsBookedCounter() + 1);
        commonInfo.update(info);
        return info.getTicketsBookedCounter();
    }

    @Override
    public List<CommonInformation> getAllCommonInformation() {
        return commonInfo.getAll();
    }

    @Override
    public String getTextInfo(List<CommonInformation> list) {
        StringBuilder text = new StringBuilder();
        getAllCommonInformation().forEach(info ->  {

            text.append("Event '").append(eventService.getEventById(info.getEventId()).getName()).
                    append("'\n");
            text.append("\tEvent access by name count: ").append(info.getAccessedByNameCounter()).append("\n");
            text.append("\tEvent price queried count: ").append(info.getPriceQueriedCounter()).append("\n");
            text.append("\tEvent booked tickets count: ").append(info.getTicketsBookedCounter()).append("\n");
        });
        return text.toString();
    }
}