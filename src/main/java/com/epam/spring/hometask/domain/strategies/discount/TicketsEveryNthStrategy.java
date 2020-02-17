package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TicketsEveryNthStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.tickets.everyNth.rule}")
    private int condition;

    public TicketsEveryNthStrategy(@Value("${discount.tickets.everyNth.value}") double discountValue) {
        super(discountValue, "Every Nth ticket discount");
    }
    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return ticket.getId() % this.condition == 0 ? this.getDiscountValue() : -1.0D;
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