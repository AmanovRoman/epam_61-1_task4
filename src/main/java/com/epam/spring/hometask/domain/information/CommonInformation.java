package com.epam.spring.hometask.domain.information;

import com.epam.spring.hometask.domain.DomainId;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class CommonInformation extends DomainId {
    private int eventId;
    private int accessedByNameCounter = 0;
    private int priceQueriedCounter = 0;
    private int ticketsBookedCounter = 0;

    public CommonInformation() {
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setAccessedByNameCounter(int accessedByNameCounter) {
        this.accessedByNameCounter = accessedByNameCounter;
    }

    public void setPriceQueriedCounter(int priceQueriedCounter) {
        this.priceQueriedCounter = priceQueriedCounter;
    }

    public void setTicketsBookedCounter(int ticketsBookedCounter) {
        this.ticketsBookedCounter = ticketsBookedCounter;
    }

    public int getAccessedByNameCounter() {
        return this.accessedByNameCounter;
    }

    public int getPriceQueriedCounter() {
        return this.priceQueriedCounter;
    }

    public int getTicketsBookedCounter() {
        return this.ticketsBookedCounter;
    }
}
