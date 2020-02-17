package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import java.util.Objects;
import org.springframework.stereotype.Component;

@Component
public class NoDiscountStrategy extends AbstractStrategy implements DiscountStrategy {
    public NoDiscountStrategy() {
        super(0.0D, "No discount");
    }

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        return 0.0D;
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
