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

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return ticket.getId() % this.condition == 0 ? this.getDiscountValue() : -1.0D;
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
        } else if (!(o instanceof TicketsEveryNthStrategy)) {
            return false;
        } else {
            TicketsEveryNthStrategy that = (TicketsEveryNthStrategy) o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}