package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.domain.Auditorium;
import com.epam.spring.hometask.service.business.AuditoriumServiceDao;
import com.epam.spring.hometask.service.domain.AuditoriumDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuditoriumServiceDaoImpl implements AuditoriumServiceDao {

    @Autowired
    AuditoriumDao auditoriumDao;

    @Override
    public Auditorium getAuditoriumById(int id) {
        return auditoriumDao.getById(id);
    }

    @Override
    public List<Auditorium> getAllAuditoriums() {
        return auditoriumDao.getAll();
    }

    @Override
    public Set<Integer> getSeats(int audId, List<Integer> seats) {
        Auditorium auditorium = auditoriumDao.getById(audId);
        return seats.stream().filter(seat -> seat <= auditorium.getNumberOfSeats() && seat > 0).collect(Collectors.toSet());
    }

    @Override
    public int addNewAuditorium(String name, Integer numberOfSeats, Set<Integer> vipSeats, Double vipSeatsMultiplier) {
        return auditoriumDao.save(new Auditorium(name, numberOfSeats, vipSeats, vipSeatsMultiplier));
    }

    @Override
    public int addNewAuditorium(Auditorium auditorium) {
        return auditoriumDao.save(auditorium);
    }
}
