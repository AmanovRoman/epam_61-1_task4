package com.epam.spring.hometask.service.business;

import com.epam.spring.hometask.domain.User;
import java.util.List;

public interface UserServiceDao {
    User getUserByEmail(String var1);

    User getUserById(int var1);

    int addNewUser(String var1, String var2, String var3, int var4);

    int addNewUser(User var1);

    List<User> getAllUsers();

    void setUserBirthday(int var1, String var2);

    User deleteUserById(int var1);
}
