package com.epam.spring.hometask.dao;

import java.util.List;

public interface AbstractDomainObjectDao<T> {
    int save(T object);

    T remove(int object);

    T getById(int object);

    List<T> getAll();
}