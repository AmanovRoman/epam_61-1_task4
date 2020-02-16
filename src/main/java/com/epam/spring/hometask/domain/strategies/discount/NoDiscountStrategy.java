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

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        return 0.0D;
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
        } else if (!(o instanceof LuckyWinnerStrategy)) {
            return false;
        } else {
            LuckyWinnerStrategy that = (LuckyWinnerStrategy)o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}
