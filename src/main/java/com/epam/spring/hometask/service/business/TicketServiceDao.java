package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Ticket;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public interface TicketServiceDao {
    List<Ticket> bookTickets(int scheduledId, int userId, Set<Integer> seats);

    Ticket getTicketById(int ticketId);

    List<Ticket> getAllSoldTickets();

    default List<Ticket> getTicketsByUser(int userId) {
        return filterTicketsByUser(getAllSoldTickets(), userId);
    }

    default List<Ticket> getTicketsByEvent(int eventId) {
        return filterTicketsByEvent(getAllSoldTickets(), eventId);
    }

    default List<Ticket> getTicketsByTime(LocalDateTime dateTime) {
        return filterTicketsByTime(getAllSoldTickets(), dateTime);
    }

    default List<Ticket> getTicketsByScheduledEvent(int scheduledId) {
        return filterTicketsByScheduled(getAllSoldTickets(), scheduledId);
    }

    List<Ticket> filterTicketsByEvent(List<Ticket> tickets, int eventId);

    default List<Ticket> filterTicketsByUser(List<Ticket> tickets, int userId) {
        return tickets.stream().filter((ticket) -> ticket.getUserId() == userId).collect(Collectors.toList());
    }

    List<Ticket> filterTicketsByTime(List<Ticket> tickets, LocalDateTime dateTime);

    default List filterTicketsByScheduled(List<Ticket> tickets, int scheduledId) {
        return tickets.stream().filter(ticket -> ticket.getScheduledEventId() == scheduledId).collect(Collectors.toList());
    }

    default int getTotalTickets(List<Ticket> tickets) {
        return tickets.size();
    }

    default double getTicketsTotalCost(List<Ticket> tickets) {
        return round(tickets.stream().mapToDouble(Ticket::getPrice).sum(), 2);
    }

    Ticket calculateTicketPrice(Ticket ticket, int ticketsAmount);

    default double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        } else {
            BigDecimal bd = BigDecimal.valueOf(value);
            bd = bd.setScale(places, RoundingMode.HALF_UP);
            return bd.doubleValue();
        }
    }
}
