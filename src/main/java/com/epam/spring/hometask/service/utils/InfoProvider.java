package com.epam.spring.hometask.service.utils;

import com.epam.spring.hometask.service.CommonInfoService;
import com.epam.spring.hometask.service.DiscountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InfoProvider {
    @Autowired
    CommonInfoService commonInfoService;
    @Autowired
    DiscountInfoService discountInfoService;

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