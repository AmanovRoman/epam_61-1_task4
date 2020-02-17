package com.epam.spring.hometask.application.view.shell;

import com.epam.spring.hometask.domain.Ticket;
import com.epam.spring.hometask.service.business.*;
import com.epam.spring.hometask.service.domain.AuditoriumDao;
import com.epam.spring.hometask.service.domain.DataCleanerDao;
import com.epam.spring.hometask.service.utils.InfoProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * @author Roman_Amanov
 * <p>
 * Class uses for Common usable controlls
 * Type 'help' in shell to see all commands
 */

@ShellComponent
public class CommonCommands {
    @Autowired
    ApplicationContext context;

    @Autowired
    private UserServiceDao userService;

    @Autowired
    private EventServiceDao eventService;

    @Autowired
    private ScheduledServiceDao scheduledService;

    @Autowired
    private AuditoriumServiceDao auditoriumService;

    @Autowired
    private AuditoriumDao auditoriumDao;

    @Autowired
    private TicketServiceDao ticketService;

    @Autowired
    private InfoProvider infoProvider;

    @Autowired
    DataCleanerDao dataCleaner;
    @ShellMethod(value = "Automatic clears and refill database by default params", key = "auto")
    public void autoFill() {

        clearData();

        System.out.println("\nUSERS:\n----------------------------------------");
        userService.addNewUser("Admin", "Adminych", "jena@jizni.net", 1);
        userService.addNewUser("User", "Userovich", "takie-dela@mail.to", 2);
        userService.setUserBirthday(2, "1983-02-24");
        userService.getAllUsers().forEach(System.out::println);

        System.out.println("\nAUDITORIUMS:\n----------------------------------------");
        auditoriumService.addNewAuditorium("First studio",20, new HashSet<>(Arrays.asList(1,2,3,4,5)), 1.5);
        auditoriumService.addNewAuditorium("Second studio",30, new HashSet<>(Arrays.asList(9,10,13,14)), 1.9);
        auditoriumService.getAllAuditoriums().forEach(System.out::println);

        System.out.println("\nEVENTS:\n----------------------------------------");
        eventService.addNewEvent("The Hobbit", 11, 3, 1);
        eventService.addNewEvent("Spring is coming", 5.28, 3, 1);
        eventService.getAllEvents().forEach(System.out::println);

        System.out.println("\nSCHEDULED EVENTS:\n----------------------------------------");
        scheduledService.setNewEventSchedule(1, 1, "2020-01-01 20:10", 1.5, 2);
        scheduledService.setNewEventSchedule(1, 2, "2020-01-02 20:10", 1.9, 2);
        scheduledService.getAllScheduled().forEach(System.out::println);

        System.out.println("\nTICKETS:\n----------------------------------------");
        List<Ticket> tickets = ticketService.bookTickets(1, 0, new HashSet<>(Arrays.asList(1, 3, 5, 7)));
        List<Ticket> tickets2 = ticketService.bookTickets(2, 1, new HashSet<>(Arrays.asList(1, 2, 3, 10, 15, 20)));
        List<Ticket> tickets3 = ticketService.bookTickets(2, 2, new HashSet<>(Arrays.asList(8, 13, 14)));
        ticketService.getAllSoldTickets().forEach(System.out::println);
        System.out.println("\nTOTAL COST (User 1): " + ticketService.getTicketsTotalCost(tickets));
        System.out.println("TOTAL COST (User 2): " + ticketService.getTicketsTotalCost(tickets2));
        System.out.println("TOTAL COST (User 3): " + ticketService.getTicketsTotalCost(tickets3));

    }

    @ShellMethod(value = "Show discount information", key = "info discount")
    public String showDiscountInfo() {
        return infoProvider.getDiscountSummary();
    }

    @ShellMethod(value = "Show events information", key = "info event")
    public String showEventInfo() {
        return infoProvider.getCommonSummary();
    }

    @ShellMethod(value = "Clear all database data", key = "clear all")
    public String clearData() {
        dataCleaner.cleanAllData();
        return "Database cleared";
    }

    @ShellMethod(value = "Clear all data in table (tableName)", key = "clear table")
    public String clearTableData(String tableName) {
        dataCleaner.cleanDataTable(tableName);
        return "Database cleared";
    }

}
