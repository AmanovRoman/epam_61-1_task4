package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.business.DiscountServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountServiceDao {
    private List<DiscountStrategy> strategies;

    @Autowired
    public DiscountServiceImpl(List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public DiscountStrategy getDiscount(User user, ScheduledEvents schedule, int numberOfTickets, Ticket ticket) {
        return strategies.
                stream().
                filter(strategy -> strategy.getDiscountValue() < 100.0D).
                max(Comparator.comparingDouble(
                        o -> o.calculate(user, schedule, numberOfTickets, ticket))
                ).get();
    }
}
