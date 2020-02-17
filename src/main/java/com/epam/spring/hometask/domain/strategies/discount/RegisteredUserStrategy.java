package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class RegisteredUserStrategy extends AbstractStrategy implements DiscountStrategy {

    public RegisteredUserStrategy(@Value("${discount.registeredUser.value}") double discountValue) {
        super(discountValue, "Registered user discount");
    }
    @Override
    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) {
            return -1.0D;
        } else {
            this.setUser(user);
            return this.getDiscountValue();
        }
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