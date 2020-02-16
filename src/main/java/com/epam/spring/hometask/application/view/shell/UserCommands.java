package com.epam.spring.hometask.application.view.shell;

import com.epam.spring.hometask.service.business.UserServiceDao;
import java.io.PrintStream;
import java.util.List;
import java.util.function.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class UserCommands {
    @Autowired
    UserServiceDao userService;

    @ShellMethod(
            value = "Show whole users list",
            key = {"users"}
    )
    public void showUsers() {
        System.out.println("\nUSERS:\n----------------------------------------");
        userService.getAllUsers().forEach(System.out::println);
    }

    @ShellMethod(
            value = "Create new user (fName, sName, Email, userType = 1-admin, 2-visitor)",
            key = {"user add"}
    )
    public String addUser(String firstName, String lastName, String email, int userType) {
        userService.addNewUser(firstName, lastName, email, userType);
        return "User added";
    }

    @ShellMethod(
            value = "Get user by ID (id)",
            key = {"user get"}
    )
    public String getUserById(int id) {
        return userService.getUserById(id).toString();
    }

    @ShellMethod(
            value = "Get user by email (email)",
            key = {"user search"}
    )
    public String getUserByEmail(String email) {
        return userService.getUserByEmail(email).toString();
    }

    @ShellMethod(
            value = "Delete user (id)",
            key = {"user remove"}
    )
    public String userRemove(int id) {
        return "User deleted:" + userService.deleteUserById(id);
    }

    @ShellMethod(
            value = "Add user birthday (userId, birthday='yyyy-MM-dd')",
            key = {"user birth"}
    )
    public String setUserBirthday(int id, String birthday) {
        this.userService.setUserBirthday(id, birthday);
        return "User updated";
    }
}