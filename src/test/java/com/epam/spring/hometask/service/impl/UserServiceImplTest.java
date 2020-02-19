package com.epam.spring.hometask.service.impl;

import com.epam.spring.hometask.config.CommonConfiguration;
import com.epam.spring.hometask.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = CommonConfiguration.class)
class UserServiceImplTest {

    @Autowired
    UserService userService;

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