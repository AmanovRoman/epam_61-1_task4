package com.epam.spring.hometask.service.domain;

import com.epam.spring.hometask.domain.Auditorium;
import java.util.List;

public interface AuditoriumDao {
    int save(Auditorium auditorium);

    List<Auditorium> getAll();

    Auditorium getById(int id);
}
