package com.epam.spring.hometask.service;

import com.epam.spring.hometask.domain.User;
import java.util.List;

public interface UserService {
    User getUserByEmail(String email);

    User getUserById(int userId);

    int addNewUser(String fName, String lName, String email, int userType);

    int addNewUser(User user);

    List<User> getAllUsers();

    void setUserBirthday(int userId, String date);

    User deleteUserById(int userId);
}
