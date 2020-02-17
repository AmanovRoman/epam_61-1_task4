package com.epam.spring.hometask.domain;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Scope("prototype")
public class ScheduledEvents extends DomainId {
    private int eventId;
    private int auditoriumId;
    private LocalDateTime eventTime;
    private double ticketPriceMultiplier;

    public ScheduledEvents() {
    }

    public ScheduledEvents(int eventId, int auditoriumId, LocalDateTime eventTime, double ticketPriceMultiplier) {
        this.eventId = eventId;
        this.auditoriumId = auditoriumId;
        this.eventTime = eventTime;
        this.ticketPriceMultiplier = ticketPriceMultiplier;
    }

    public int getEventId() {
        return this.eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public int getAuditoriumId() {
        return this.auditoriumId;
    }

    public void setAuditoriumId(int auditoriumId) {
        this.auditoriumId = auditoriumId;
    }

    public LocalDateTime getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(LocalDateTime eventTime) {
        this.eventTime = eventTime;
    }

    public double getTicketPriceMultiplier() {
        return this.ticketPriceMultiplier;
    }

    public void setTicketPriceMultiplier(double ticketPriceMultiplier) {
        this.ticketPriceMultiplier = ticketPriceMultiplier;
    }
    @Override
    public String toString() {
        return "ScheduledEvents{Id=" + this.getId() + ", eventId=" + this.eventId + ", auditoriumId=" + this.auditoriumId + ", eventTime=" + this.eventTime + ", ticketPriceMultiplier=" + this.ticketPriceMultiplier + '}';
    }
}
