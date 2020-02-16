package com.epam.spring.hometask.domain;

import com.epam.spring.hometask.domain.enums.UserType;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

@Component
@Scope("prototype")
public class User extends DomainId {
    private String firstName;
    private String lastName;
    private String email;
    private int userType;
    private LocalDate birthDate;

    public User() {
    }

    public User(String firstName, String lastName, String email, int userType) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.userType = userType;
    }

    public LocalDate getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getUserType() {
        return this.userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (!(o instanceof User)) {
            return false;
        } else {
            User user = (User)o;
            return this.getUserType() == user.getUserType() && Objects.equals(this.getFirstName(), user.getFirstName()) && Objects.equals(this.getLastName(), user.getLastName()) && Objects.equals(this.getEmail(), user.getEmail());
        }
    }

    public int hashCode() {
        return Objects.hash(this.getFirstName(), this.getLastName(), this.getEmail(), this.getUserType());
    }

    public String toString() {
        return "User{Id='" + this.getId() + ", firstName='" + this.firstName + '\'' + ", lastName='" + this.lastName + '\'' + ", email='" + this.email + '\'' + ", userType=" + UserType.values()[this.userType - 1] + ", birthday=" + this.birthDate + '}';
    }
}
