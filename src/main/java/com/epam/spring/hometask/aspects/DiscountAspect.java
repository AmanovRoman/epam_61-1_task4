package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.information.DiscountInformation;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.DiscountInfoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class DiscountAspect {
    @Autowired
    ApplicationContext context;
    @Autowired
    DiscountInfoService discountInfoService;

    @Around("(execution( * *..*.setDiscount(com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy)))")
    public Object countDiscountInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        DiscountStrategy strategy = (DiscountStrategy) joinPoint.getArgs()[0];
        User user = strategy.getLastUser();
        List<DiscountInformation> infoList = discountInfoService.findByUserId((user != null) ? user.getId() : 0);
        DiscountInformation info = null;

        if (infoList.size() > 0)
            info = discountInfoService.filterByStrategyName(infoList, strategy.getDiscountTitle());

        if (info == null) {
            info = (DiscountInformation) context.getBean("discountInformation");
            info.setUserId((user==null)?0:user.getId());
            info.setStrategyName(strategy.getDiscountTitle());
            info.setId(discountInfoService.saveInfo(info));
        }

        discountInfoService.increaseCounter(info);
        return joinPoint.proceed();
    }
}