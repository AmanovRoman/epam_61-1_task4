package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.domain.Event;
import com.epam.spring.hometask.domain.ScheduledEvents;
import com.epam.spring.hometask.domain.enums.UserType;
import java.time.LocalDateTime;
import java.util.List;

public interface ScheduledServiceDao {
    int setNewEventSchedule(ScheduledEvents var1, UserType var2);

    int setNewEventSchedule(int var1, int var2, String var3, double var4, int var6);

    int setNewEventSchedule(Event var1, Auditorium var2, LocalDateTime var3, double var4, UserType var6);

    List<ScheduledEvents> getScheduledByEventId(int var1);

    List<ScheduledEvents> getAllScheduled();

    List<ScheduledEvents> getScheduledByDate(LocalDateTime var1);

    ScheduledEvents getScheduledById(int var1);

    List<ScheduledEvents> getScheduledByAuditorium(int var1);

    List<ScheduledEvents> filterByEventId(List<ScheduledEvents> var1, int var2);

    List<ScheduledEvents> filterByDate(List<ScheduledEvents> var1, LocalDateTime var2);

    List<ScheduledEvents> filterByAuditorium(List<ScheduledEvents> var1, int var2);
}
