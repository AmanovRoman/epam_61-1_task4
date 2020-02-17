package com.epam.spring.hometask.service.business.impl;

import com.epam.spring.hometask.config.CommonConfiguration;
import com.epam.spring.hometask.service.business.UserServiceDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommonConfiguration.class)
class UserServiceDaoImplTest {

    @Autowired
    UserServiceDao userService;

    @Test
    void getUserByEmail() {
    }

    @Test
    void addNewUser() {
        userService.addNewUser("qwe", "qwe", "qwe", 1);
    }

    @Test
    void addNewUser1() {
    }

    @Test
    void getAllUsers() {
    }

    @Test
    void setUserBirthday() {
    }

    @Test
    void deleteUserById() {
    }

    @Test
    void getUserById() {
    }
}