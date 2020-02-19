package com.epam.spring.hometask.application.view.shell;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.service.EventService;
import com.epam.spring.hometask.service.ScheduledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class EventsCommands {
    @Autowired
    EventService eventService;
    @Autowired
    ScheduledService scheduledService;

    @ShellMethod(
            value = "Show whole events list",
            key = {"events"}
    )
    public void showEvents() {
        System.out.println("\nEVENTS:\n----------------------------------------");
        eventService.getAllEvents().forEach(System.out::println);
    }

    @ShellMethod(
            value = "Show event by ID (id)",
            key = {"event get"}
    )
    public Event showEventById(int id) {
        return eventService.getEventById(id);
    }

    @ShellMethod(
            value = "Show event by name (name)",
            key = {"event search"}
    )
    public Event showEventByName(String name) {
        return eventService.getEventByName(name);
    }

    @ShellMethod(
            value = "New event creation (name, basePrice, eventRating = 1-LOW, 2 - MID, 3 - HIGH, userId)",
            key = {"event new"}
    )
    public String newEvent(String name, double basePrice, int rating, int userId) {
        try {
            eventService.addNewEvent(name, basePrice, rating, userId);
            return "New event added";
        } catch (Exception e) {
            return "User have no permissions for this operation";
        }
    }

    @ShellMethod(
            value = "Schedule event (eventId, auditoryId, time='yyyy-MM-dd HH:mm', multiplier (i.e: 1.3), userId)",
            key = {"event bind"}
    )
    public String scheduleEvent(int eventId, int audId, String time, double mult, int userId) {
        try {
            scheduledService.setNewEventSchedule(eventId, audId, time, mult, userId);
            return "New scheduled event";
        } catch (IllegalArgumentException | NullPointerException var8) {
            return "Wrong arguments or User have no permissions for this operation";
        }
    }

    @ShellMethod(
            value = "Show scheduled events",
            key = {"scheduled"}
    )
    public void showScheduledEvents() {
        System.out.println("\nSCHEDULED EVENTS:\n----------------------------------------");
        scheduledService.getAllScheduled().forEach(System.out::println);
    }

    @ShellMethod(
            value = "Show event timetable (eventId)",
            key = {"event time"}
    )
    public void showEventTimetable(int eventId) {
        System.out.println("\nEVENT TIMETABLE:\n----------------------------------------");
        scheduledService.getScheduledByEventId(eventId).forEach(System.out::println);
    }
}
