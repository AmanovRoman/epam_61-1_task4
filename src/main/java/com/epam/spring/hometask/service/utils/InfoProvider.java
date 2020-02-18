package com.epam.spring.hometask.service.utils;

import com.epam.spring.hometask.service.business.CommonInfoServiceDao;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class InfoProvider {
    @Autowired
    CommonInfoServiceDao commonInfoService;
    @Autowired
    DiscountInfoServiceDao discountInfoService;

    public InfoProvider() {
    }

    public String getDiscountSummary() {
        return "\nDISCOUNT USAGE SUMMARY:\n---------------------------------------\n" +
                discountInfoService.getTextInfo();

    }

    public String getCommonSummary() {
        return "\nEVENTS COMMON SUMMARY:\n---------------------------------------\n" +
                commonInfoService.getTextInfo(commonInfoService.getAllCommonInformation());
    }
}