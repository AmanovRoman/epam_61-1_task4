package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Scope("prototype")
public class Ticket extends DomainId {
    private int userId;
    private int scheduledEventId;
    private int seat;
    private double price;
    private String discount;
    private double discountValue;

    public Ticket() {
    }

    public Ticket(int userId, int scheduledEventId, int seat) {
        this.userId = userId;
        this.scheduledEventId = scheduledEventId;
        this.seat = seat;
    }

    public int getSeat() {
        return this.seat;
    }

    public void setSeat(int seat) {
        this.seat = seat;
    }

    public int getUserId() {
        return this.userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getScheduledEventId() {
        return this.scheduledEventId;
    }

    public void setScheduledEventId(int scheduledEventId) {
        this.scheduledEventId = scheduledEventId;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDiscount() {
        return this.discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public void setDiscount(DiscountStrategy discount) {
        this.discount = discount.getDiscountTitle();
    }

    public double getDiscountValue() {
        return this.discountValue;
    }

    public void setDiscountValue(double discountValue) {
        this.discountValue = discountValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Ticket)) {
            return false;
        } else {
            Ticket ticket = (Ticket) o;
            return this.getUserId() == ticket.getUserId() && this.getScheduledEventId() == ticket.getScheduledEventId() && this.getSeat() == ticket.getSeat();
        }
    }
    @Override
    public int hashCode() {
        return Objects.hash(this.getUserId(), this.getScheduledEventId(), this.getSeat());
    }
    @Override
    public String toString() {
        return "Ticket{ Id=" + this.getId() + ", userId=" + this.userId + ", scheduledEventId=" + this.scheduledEventId + ", seat=" + this.seat + ", price=" + this.price + ", discount='" + this.discount + '\'' + ", discountValue=" + this.discountValue + '}';
    }
}
