package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.enums.EventRating;
import java.util.Objects;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Event extends DomainId {
    private String name;
    private double basePrice;
    private int rating;

    public Event() {
    }

    public Event(String name, double basePrice, int eventRating) {
        this.name = name;
        this.basePrice = basePrice;
        this.rating = eventRating;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBasePrice() {
        return this.basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getRating() {
        return this.rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating.getValue();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof Event)) {
            return false;
        } else {
            Event event = (Event)o;
            return Double.compare(event.getBasePrice(), this.getBasePrice()) == 0 &&
                    this.getRating() == event.getRating() &&
                    Objects.equals(this.getName(), event.getName());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getName(), this.getBasePrice(), this.getRating());
    }

    public String toString() {
        return "Event{ id=" + this.getId() + ", name='" + this.name + '\'' + ", basePrice=" + this.basePrice + ", rating=" + EventRating.values()[this.rating - 1] + '}';
    }
}
