package com.epam.spring.hometask.dao;

import com.epam.spring.hometask.domain.User;

public interface UserDao extends AbstractDomainObjectDao<User> {
    User getUserByEmail(String var1);

    boolean update(User var1);
}
