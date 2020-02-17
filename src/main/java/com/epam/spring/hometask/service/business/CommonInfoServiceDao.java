package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.information.CommonInformation;
import java.util.List;

public interface CommonInfoServiceDao {
    int saveInfo(CommonInformation info);

    CommonInformation findByEventId(int eventId);

    int increaseAccessedByName(CommonInformation info);

    int increasePriceQueried(CommonInformation info);

    int increaseTicketBooked(CommonInformation info);

    List<CommonInformation> getAllCommonInformation();

    String getTextInfo(List<CommonInformation> info);
}