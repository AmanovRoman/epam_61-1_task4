package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.information.CommonInformation;
import java.util.List;

public interface CommonInfoServiceDao {
    int saveInfo(CommonInformation var1);

    CommonInformation findByEventId(int var1);

    int increaseAccessedByName(CommonInformation var1);

    int increasePriceQueried(CommonInformation var1);

    int increaseTicketBooked(CommonInformation var1);

    List<CommonInformation> getAllCommonInformation();

    String getTextInfo(List<CommonInformation> var1);
}