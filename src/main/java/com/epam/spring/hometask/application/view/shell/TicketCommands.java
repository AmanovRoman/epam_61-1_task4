package com.epam.spring.hometask.application.view.shell;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Roman_Amanov
 * <p>
 * Class uses for Tickets controlls
 * Type 'help' in shell to see all commands
 */


@ShellComponent
public class TicketCommands {

    @Autowired
    TicketService ticketService;

    @ShellMethod(value = "Show whole tickets list", key = "tickets")
    public void showTickets() {
        System.out.println("\nTICKETS:\n----------------------------------------");
        ticketService.getAllSoldTickets().forEach(System.out::println);
    }


    @ShellMethod(value = "Buy ticket (Registered user) (userId, scheduledEventId, seats[10])", key = "ticket buy_r")
    public String buyTicketsRegistered(int userId, int scheduledId, @ShellOption(arity = 10) Integer[] seats) {
        return buyTickets(userId, scheduledId, seats);
    }

    @ShellMethod(value = "Buy ticket (Not registered user) (scheduledEventId, seats[10])", key = "ticket buy")
    public String buyTicketsNotRegistered(int scheduledId, @ShellOption(arity = 10) Integer[] seats) {
        return buyTickets(0, scheduledId, seats);
    }

    private String buyTickets(int userId, int scheduledId, Integer[] seats) {
        List<Ticket> tickets = ticketService.bookTickets(scheduledId, userId, new HashSet<>(Arrays.asList(seats)));
        return "NEW TICKETS: \n-------------------------------------------" +
                tickets.stream().
                        map(ticket -> ticket.toString() + "\n").
                        collect(Collectors.joining());
    }

    // showUserTickets
    @ShellMethod(value = "Show user tickets list by scheduled (userId, scheduledId)", key = "tickets user")
    public String showUserScheduledTickets(int userId, int scheduledId) {
        return "TICKETS BY USERID=" + userId + " SCHEDULED=" + scheduledId + "\n-----------------------------------------------" +
                ticketService.filterTicketsByScheduled(ticketService.getTicketsByUser(userId), scheduledId).stream().
                        map(ticket -> ticket.toString() + "\n").
                        collect(Collectors.joining());
    }

    @ShellMethod(value = "Show scheduled event tickets count (scheduledEventId)", key = "tickets sum")
    public String showTicketsSummaryByScheduled(int scheduledId) {
        return "TICKETS SUMMARY (SCHEDULED ID=" + scheduledId + ")\n----------------------------------------------\n" +
                ticketService.getTicketsByScheduledEvent(scheduledId).stream().
                        map(ticket -> ticket.toString() + "\n").
                        collect(Collectors.joining()) +
                "\n\tTOTAL TICKETS SOLD: " + ticketService.getTotalTickets(ticketService.getTicketsByScheduledEvent(scheduledId)) +
                "\n\tTOTAL TICKETS COST: " + ticketService.getTicketsTotalCost(ticketService.getTicketsByScheduledEvent(scheduledId));
    }

    @ShellMethod(value = "Show event tickets count (eventId)", key = "tickets event")
    public String showTicketsSummaryByEvent(int eventId) {
        return "TICKETS SUMMARY (EVENT ID=" + eventId + ")\n----------------------------------------------\n" +
                ticketService.getTicketsByEvent(eventId).stream().
                        map(ticket -> ticket.toString() + "\n").
                        collect(Collectors.joining()) +
                "\n\tTOTAL EVENT TICKETS SOLD: " + ticketService.getTotalTickets(ticketService.getTicketsByEvent(eventId)) +
                "\n\tTOTAL EVENT TICKETS COST: " + ticketService.getTicketsTotalCost(ticketService.getTicketsByEvent(eventId));
    }
}
