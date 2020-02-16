package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import java.util.List;

public interface EventServiceDao {
    Event getEventById(int id);

    Event getEventByName(String name);

    List<Event> getAllEvents();

    int addNewEvent(Event event, User user);

    int addNewEvent(String name, double cost, int var4, int var5);
}
