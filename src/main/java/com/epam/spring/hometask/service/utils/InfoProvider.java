package com.epam.spring.hometask.service.utils;

import com.epam.spring.hometask.service.business.CommonInfoServiceDao;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import org.springframework.beans.factory.annotation.Autowired;

public class InfoProvider {
    @Autowired
    static CommonInfoServiceDao commonInfoService;
    @Autowired
    static DiscountInfoServiceDao discountInfoService;

    public InfoProvider() {
    }

    public static String getDiscountSummary() {
        StringBuilder info = new StringBuilder("\nDISCOUNT USAGE SUMMARY:\n---------------------------------------\n");
        return info.toString();
    }

    public static String getCommonSummary() {
        return "\nEVENTS COMMON SUMMARY:\n---------------------------------------\n" +
                commonInfoService.getTextInfo(commonInfoService.getAllCommonInformation());
    }
}