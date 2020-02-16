package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.*;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.business.*;
import com.epam.spring.hometask.service.domain.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Repository
public class TicketServiceDaoImpl implements TicketServiceDao {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private TicketDao ticketDao;

    private Ticket bookTicket(Integer userId, int scheduledId, int seat, int ticketsAmount) {
        User user = ((UserServiceDao) context.getBean("userServiceDaoImpl")).
                getUserById(userId);

        ScheduledEvents scheduled = ((ScheduledServiceDao) context.getBean("scheduledServiceDaoImpl")).
                getScheduledById(scheduledId);

        if (scheduled == null) return null;
        Ticket ticket = (Ticket) context.getBean("ticket");
        ticket.setUserId((user == null) ? 0 : user.getId());
        ticket.setSeat(seat);
        ticket.setScheduledEventId(scheduled.getId());
        calculateTicketPrice(ticket, ticketsAmount);
        ticketDao.save(ticket);
        return ticket;
    }

    public List<Ticket> bookTickets(int scheduledId, int userId, Set<Integer> seats) {
        List<Ticket> tickets = new ArrayList<>();
        for (Integer seat : seats) {
            Ticket ticket = this.bookTicket(userId, scheduledId, seat, seats.size());
            tickets.add(ticket);
        }
        return tickets;
    }

    public Ticket getTicketById(int ticketId) {
        return ticketDao.getById(ticketId);
    }

    public List<Ticket> getAllSoldTickets() {
        return this.ticketDao.getAll();
    }

    public List<Ticket> filterTicketsByEvent(List<Ticket> tickets, int eventId) {
        return null;
    }

    public List<Ticket> filterTicketsByTime(List<Ticket> tickets, LocalDateTime dateTime) {
        return null;
    }

    public Ticket calculateTicketPrice(Ticket ticket, int ticketsAmount) {
        ScheduledEvents scheduled = ((ScheduledServiceDao) this.context.getBean("scheduledServiceDaoImpl")).
                getScheduledById(ticket.getScheduledEventId());

        Auditorium auditorium = ((AuditoriumServiceDao) this.context.getBean("auditoriumServiceDaoImpl")).
                getAuditoriumById(scheduled.getAuditoriumId());

        Event event = ((EventServiceDao) this.context.getBean("eventServiceDaoImpl")).
                getEventById(scheduled.getEventId());

        User user = ((UserServiceDao) this.context.getBean("userServiceDaoImpl")).
                getUserById(ticket.getUserId());

        if (isNull(scheduled) || isNull(auditorium) || isNull(event))
            throw new IllegalArgumentException();

        double vipMultiplier = (auditorium.getVipSeats().contains(ticket.getSeat())) ? auditorium.getVipSeatsMultiplier() : 1;
        DiscountServiceDao discountService = (DiscountServiceDao) context.getBean("discountServiceImpl");
        DiscountStrategy discount = discountService.getDiscount(user, scheduled, ticketsAmount, ticket);
        double maxDiscount = 0;
        if (Objects.nonNull(discount)) {
            maxDiscount = discount.calculate(user, scheduled, ticketsAmount, ticket);
            ticket.setDiscount(discount);
            ticket.setDiscountValue(maxDiscount);
        }

        ticket.setPrice(round((event.getBasePrice() * scheduled.getTicketPriceMultiplier() * vipMultiplier) * ((100 - maxDiscount) / 100), 2));
        return ticket;
    }
}