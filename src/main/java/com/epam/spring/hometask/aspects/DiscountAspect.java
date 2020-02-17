package com.epam.spring.hometask.aspects;

import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.information.DiscountInformation;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.business.DiscountInfoServiceDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class DiscountAspect {
    @Autowired
    ApplicationContext context;
    @Autowired
    DiscountInfoServiceDao discountInfoService;

    @Around("(execution( * *..*.setDiscount(..)))")
    public Object countDiscountInfo(ProceedingJoinPoint joinPoint) throws Throwable {
        DiscountStrategy strategy = (DiscountStrategy)joinPoint.getArgs()[0];
        User user = strategy.getLastUser();
        DiscountInformation info = discountInfoService.filterByStrategyName(discountInfoService.findByUserId(user.getId()), strategy.getDiscountTitle());
        if (info == null) {
            info = (DiscountInformation)context.getBean("discountInformation");
        }

        this.discountInfoService.increaseCounter(info);
        return joinPoint.proceed();
    }
}