package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Lazy
public class LuckyWinnerStrategy extends AbstractStrategy implements DiscountStrategy {
    private static LuckyWinnerStrategy instance;

    private LuckyWinnerStrategy() {
        super(100.0D, "Lucky Winner discount");
    }

    public static LuckyWinnerStrategy getInstance() {
        if (instance == null) {
            instance = new LuckyWinnerStrategy();
        }

        return instance;
    }

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return this.getDiscountValue();
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
            LuckyWinnerStrategy that = (LuckyWinnerStrategy) o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}
