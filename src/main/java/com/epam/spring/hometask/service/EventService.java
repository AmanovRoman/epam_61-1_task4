package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import java.util.List;

public interface EventService {
    Event getEventById(int eventId);

    Event getEventByName(String name);

    List<Event> getAllEvents();

    int addNewEvent(Event event, User user);

    int addNewEvent(String name, double basePrice, int eventRating, int userId);
}
