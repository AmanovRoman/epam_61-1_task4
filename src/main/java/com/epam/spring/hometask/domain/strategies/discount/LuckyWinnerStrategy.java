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

    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        this.setUser(user);
        return this.getDiscountValue();
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
