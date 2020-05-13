package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserDAOImpl implements UserDAO {
    @Override
    public User createUser(String firstName, String lastName, String email) {
        User
                newUser =
                new User();
        newUser.setFirstName(firstName);
        newUser.setLastName(lastName);
        newUser.setEmail(email);
        return newUser;
    }
}
