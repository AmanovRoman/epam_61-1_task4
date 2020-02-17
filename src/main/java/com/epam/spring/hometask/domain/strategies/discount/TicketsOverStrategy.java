package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class TicketsOverStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.tickets.over.rule}")
    private int everyOver;

    public TicketsOverStrategy(@Value("${discount.tickets.over.value}") double discountValue) {
        super(discountValue, "Mass tickets discount");
    }
    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return ticketsAmount > this.everyOver ? this.getDiscountValue() : -1.0D;
    }
    @Override
    public String getDiscountTitle() {
        return this.getStrategyName();
    }
    @Override
    public double getDiscountValue() {
        return super.getDiscountValue();
    }
    @Override
    public User getLastUser() {
        return this.getUser();
    }

}