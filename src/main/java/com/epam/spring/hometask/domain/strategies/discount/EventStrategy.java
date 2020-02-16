package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.service.domain.EventDao;
import java.util.Objects;
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

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        Event event = eventService.getById(scheduler.getEventId());
        Event exact = eventService.getById(this.exactValue);
        this.setUser(user);
        return event.equals(exact) ? this.getDiscountValue() : -1.0D;
    }

    public String getDiscountTitle() {
        return this.getStrategyName();
    }

    public double getDiscountValue() {
        return super.getDiscountValue();
    }

    public User getLastUser() {
        return this.getUser();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof EventStrategy)) {
            return false;
        } else {
            EventStrategy that = (EventStrategy)o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}
