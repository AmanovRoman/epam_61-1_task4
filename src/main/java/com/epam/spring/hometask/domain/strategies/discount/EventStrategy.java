package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.dao.EventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EventStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.events.rule}")
    private int exactValue;
    @Autowired
    private EventDao eventService;

    public EventStrategy(@Value("${discount.events.value}") double discountValue) {
        super(discountValue, "Event discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        Event event = eventService.getById(scheduler.getEventId());
        Event exact = eventService.getById(this.exactValue);
        this.setUser(user);
        return event.equals(exact) ? this.getDiscountValue() : -1.0D;
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
