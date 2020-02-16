package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.Auditorium;
import java.util.List;
import java.util.Set;

public interface AuditoriumServiceDao {
    Auditorium getAuditoriumById(int var1);

    List<Auditorium> getAllAuditoriums();

    Set<Integer> getSeats(int var1, List<Integer> var2);

    int addNewAuditorium(String var1, Integer var2, Set<Integer> var3, Double var4);

    int addNewAuditorium(Auditorium var1);
}