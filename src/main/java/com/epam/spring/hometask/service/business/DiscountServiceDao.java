package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;

public interface DiscountServiceDao {
    DiscountStrategy getDiscount(User var1, ScheduledEvents var2, int var3, Ticket var4);
}
