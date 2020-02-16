package com.epam.spring.hometask.domain.strategies.discount;

import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
public class BirthdayStrategy extends AbstractStrategy implements DiscountStrategy {
    @Value("${discount.birthday.rule}")
    private int beforeAfter;

    public BirthdayStrategy(@Value("${discount.birthday.value}") double discountValue) {
        super(discountValue, "Birthday discount");
    }

    public double calculate(User user, ScheduledEvents scheduler, int ticketsAmount, Ticket ticket) {
        if (Objects.isNull(user)) {
            return -1.0D;
        } else if (user.getBirthDate() == null) {
            return -1.0D;
        } else {
            this.setUser(user);
            LocalDate nowDate = LocalDate.now().minusYears((long) LocalDate.now().getYear());
            LocalDate bDate = user.getBirthDate().minusYears((long) user.getBirthDate().getYear());
            LocalDate compare1 = bDate.minusDays((long) this.beforeAfter);
            LocalDate compare2 = bDate.plusDays((long) this.beforeAfter);
            boolean a1 = compare1.isBefore(nowDate);
            boolean a2 = compare2.isAfter(nowDate);
            return a1 && a2 ? this.getDiscountValue() : -1.0D;
        }
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
        } else if (!(o instanceof BirthdayStrategy)) {
            return false;
        } else {
            BirthdayStrategy that = (BirthdayStrategy) o;
            return this.getStrategyName().equals(that.getStrategyName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getStrategyName());
    }
}
