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

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return ticketsAmount > this.everyOver ? this.getDiscountValue() : -1.0D;
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
        } else if (!(o instanceof TicketsOverStrategy)) {
            return false;
        } else {
            TicketsOverStrategy that = (TicketsOverStrategy)o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}