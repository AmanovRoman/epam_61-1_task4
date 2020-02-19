package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.Auditorium;
import java.util.List;
import java.util.Set;

public interface AuditoriumService {
    Auditorium getAuditoriumById(int auditoriumId);

    List<Auditorium> getAllAuditoriums();

    Set<Integer> getSeats(int auditoriumId, List<Integer> seatsNumbers);

    int addNewAuditorium(String name, Integer totalSeats, Set<Integer> vipSeats, Double vipCostMultiplier);

    int addNewAuditorium(Auditorium auditorium);
}