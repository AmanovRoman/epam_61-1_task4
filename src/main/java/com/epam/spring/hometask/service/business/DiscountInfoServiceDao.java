package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.information.DiscountInformation;
import java.util.List;
import java.util.function.Predicate;

public interface DiscountInfoServiceDao {

    int saveInfo(DiscountInformation info);

    List<DiscountInformation> findByUserId(Integer userId);

    List<DiscountInformation> findByStrategyName(String discount);

    default DiscountInformation filterByStrategyName(List<DiscountInformation> list, String name) {
        return list.stream().filter((discountInfo) -> discountInfo.getStrategyName().equals(name)).findFirst().orElse(null);
    }

    int increaseCounter(DiscountInformation info);

    String getTextInfo();

}
