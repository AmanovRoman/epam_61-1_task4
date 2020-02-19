package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.Event;

public interface EventDao extends AbstractDomainObjectDao<Event> {
    Event getByName(String name);
}