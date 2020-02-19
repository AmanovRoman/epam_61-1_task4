package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.enums.UserType;
import com.epam.spring.hometask.service.AuditoriumService;
import com.epam.spring.hometask.service.EventService;
import com.epam.spring.hometask.service.ScheduledService;
import com.epam.spring.hometask.service.UserService;
import com.epam.spring.hometask.dao.ScheduleEventDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduledServiceImpl implements ScheduledService {
    @Autowired
    ApplicationContext context;
    @Autowired
    ScheduleEventDao scheduleEventDao;
    @Autowired
    EventService eventService;
    @Autowired
    AuditoriumService auditoriumService;
    @Autowired
    UserService userService;

    @Override
    public int setNewEventSchedule(ScheduledEvents scheduledEvents, UserType userType) throws IllegalArgumentException {
        if (userType != UserType.ADMIN) {
            throw new IllegalArgumentException("User type must be ADMIN");
        } else {
            return this.scheduleEventDao.save(scheduledEvents);
        }
    }

    @Override
    public int setNewEventSchedule(int eventId, int auditoriumId, String eventTime, double ticketPriceMultiplier, int userId) throws NullPointerException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        User user = userService.getUserById(userId);
        return setNewEventSchedule(
                eventService.getEventById(eventId),
                auditoriumService.getAuditoriumById(auditoriumId),
                LocalDateTime.parse(eventTime, formatter),
                ticketPriceMultiplier,
                UserType.values()[user.getUserType() - 1]);
    }

    @Override
    public int setNewEventSchedule(Event event, Auditorium auditorium, LocalDateTime eventTime, double ticketPriceMultiplier, UserType userType) throws NullPointerException {
        ScheduledEvents scheduled = (ScheduledEvents) context.getBean("scheduledEvents");
        scheduled.setEventId(event.getId());
        scheduled.setAuditoriumId(auditorium.getId());
        scheduled.setEventTime(eventTime);
        scheduled.setTicketPriceMultiplier(ticketPriceMultiplier);
        return setNewEventSchedule(scheduled, userType);
    }

    @Override
    public List<ScheduledEvents> getScheduledByEventId(int eventId) {
        return filterByEventId(scheduleEventDao.getAll(), eventId);
    }

    @Override
    public List<ScheduledEvents> getAllScheduled() {
        return scheduleEventDao.getAll();
    }

    @Override
    public List<ScheduledEvents> getScheduledByDate(LocalDateTime time) {
        return filterByDate(scheduleEventDao.getAll(), time);
    }

    @Override
    public ScheduledEvents getScheduledById(int scheduledId) {
        return scheduleEventDao.getById(scheduledId);
    }

    @Override
    public List<ScheduledEvents> getScheduledByAuditorium(int auditoriumId) {
        return filterByAuditorium(scheduleEventDao.getAll(), auditoriumId);
    }

    @Override
    public List<ScheduledEvents> filterByEventId(List<ScheduledEvents> list, int eventId) {
        return list.stream().filter((scheduledEvents) -> scheduledEvents.getEventId() == eventId).collect(Collectors.toList());
    }

    @Override
    public List<ScheduledEvents> filterByDate(List<ScheduledEvents> list, LocalDateTime dateTime) {
        return list.stream().filter((scheduledEvents) -> scheduledEvents.getEventTime().equals(dateTime)).collect(Collectors.toList());
    }

    @Override
    public List<ScheduledEvents> filterByAuditorium(List<ScheduledEvents> list, int auditoriumId) {
        return list.stream().filter((scheduledEvents) -> scheduledEvents.getAuditoriumId() == auditoriumId).collect(Collectors.toList());
    }
}
