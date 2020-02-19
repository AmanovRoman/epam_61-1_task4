package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;

public interface DiscountService {
    DiscountStrategy getDiscount(User user, ScheduledEvents schedule, int numberOfTickets, Ticket ticket);
}
