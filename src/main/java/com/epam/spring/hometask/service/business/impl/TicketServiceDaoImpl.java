package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.*;
import com.epam.spring.hometask.domain.strategies.discount.DiscountStrategy;
import com.epam.spring.hometask.service.business.*;
import com.epam.spring.hometask.service.domain.TicketDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import static java.util.Objects.isNull;

@Service
public class TicketServiceDaoImpl implements TicketServiceDao {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private TicketDao ticketDao;

    @Autowired
    UserServiceDao userServiceDao;

    @Autowired
    ScheduledServiceDao scheduledService;

    @Autowired
    EventServiceDao eventService;

    @Autowired
    AuditoriumServiceDao auditoriumService;

    @Autowired
    DiscountServiceDao discountService;

    @Override
    public List<Ticket> bookTickets(int scheduledId, int userId, Set<Integer> seats) {
        List<Ticket> tickets = new ArrayList<>();
        for (Integer seat : seats) {
            Ticket ticket = this.bookTicket(userId, scheduledId, seat, seats.size());
            tickets.add(ticket);
        }
        return tickets;
    }

    @Override
    public Ticket getTicketById(int ticketId) {
        return ticketDao.getById(ticketId);
    }

    @Override
    public List<Ticket> getAllSoldTickets() {
        return this.ticketDao.getAll();
    }

    @Override
    public List<Ticket> filterTicketsByEvent(List<Ticket> tickets, int eventId) {
        return null;
    }

    @Override
    public List<Ticket> filterTicketsByTime(List<Ticket> tickets, LocalDateTime dateTime) {
        return null;
    }

    private Ticket bookTicket(Integer userId, int scheduledId, int seat, int ticketsAmount) {
        User user = userServiceDao.getUserById(userId);
        ScheduledEvents scheduled = scheduledService.getScheduledById(scheduledId);
        if (scheduled == null) return null;
        Ticket ticket = (Ticket) context.getBean("ticket");
        ticket.setUserId((user == null) ? 0 : user.getId());
        ticket.setSeat(seat);
        ticket.setScheduledEventId(scheduled.getId());
        ticket.setId(ticketDao.save(ticket));
        calculateTicketPrice(ticket, ticketsAmount);
        ticketDao.update(ticket);
        return ticket;
    }

    @Override
    public Ticket calculateTicketPrice(Ticket ticket, int ticketsAmount) {
        ScheduledEvents scheduled = scheduledService.getScheduledById(ticket.getScheduledEventId());
        Auditorium auditorium = auditoriumService.getAuditoriumById(scheduled.getAuditoriumId());
        Event event = eventService.getEventById(scheduled.getEventId());
        User user = userServiceDao.getUserById(ticket.getUserId());

        if (isNull(scheduled) || isNull(auditorium) || isNull(event))
            throw new IllegalArgumentException();

        double vipMultiplier = (auditorium.getVipSeats().contains(ticket.getSeat())) ? auditorium.getVipSeatsMultiplier() : 1;
        DiscountStrategy discount = discountService.getDiscount(user, scheduled, ticketsAmount, ticket);
        double maxDiscount = 0;

        if (Objects.nonNull(discount)) {
            maxDiscount = discount.calculate(user, scheduled, ticketsAmount, ticket);
            ticket.setDiscount(discount);
            ticket.setDiscountValue(maxDiscount);
        }

        ticket.setPrice(round((event.getTicketBasePrice() * scheduled.getTicketPriceMultiplier() * vipMultiplier) * ((100 - maxDiscount) / 100), 2));
        return ticket;
    }
}