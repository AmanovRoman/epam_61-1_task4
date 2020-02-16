package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import java.util.List;

public interface EventServiceDao {
    Event getEventById(int var1);

    Event getEventByName(String var1);

    List<Event> getAllEvents();

    int addNewEvent(Event var1, User var2);

    int addNewEvent(String var1, double var2, int var4, int var5);
}
