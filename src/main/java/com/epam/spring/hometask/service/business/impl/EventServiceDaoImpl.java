package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.User;
import com.epam.spring.hometask.domain.enums.EventRating;
import com.epam.spring.hometask.domain.enums.UserType;
import com.epam.spring.hometask.service.business.EventServiceDao;
import com.epam.spring.hometask.service.business.UserServiceDao;
import com.epam.spring.hometask.service.domain.EventDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;

@Repository
public class EventServiceDaoImpl implements EventServiceDao {
    @Autowired
    private EventDao eventDao;
    @Autowired
    private ApplicationContext context;
    @Autowired
    private UserServiceDao userService;

    @Override
    public Event getEventById(int eventId) {
        return eventDao.getById(eventId);
    }

    @Override
    public Event getEventByName(String name) {
        return eventDao.getByName(name);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventDao.getAll();
    }

    @Override
    public int addNewEvent(Event event, User user) throws IllegalArgumentException {
        try {
            if (UserType.values()[user.getUserType() - 1] != UserType.ADMIN) {
                throw new IllegalArgumentException("Wrong user");
            }
        } catch (NullPointerException var4) {
            throw new IllegalArgumentException("User type must be ADMIN");
        }

        return eventDao.save(event);
    }

    @Override
    public int addNewEvent(String name, double basePrice, int eventRating, int userId) throws IllegalArgumentException {
        EventRating rating = EventRating.values()[eventRating - 1];
        if (name.length() >= 3 && rating != null) {
            Event event = (Event)this.context.getBean("event");
            event.setName(name);
            event.setBasePrice(basePrice);
            event.setRating(rating);
            return this.addNewEvent(event, this.userService.getUserById(userId));
        } else {
            throw new IllegalArgumentException("Illegal arguments");
        }
    }
}