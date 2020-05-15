package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.User;
import org.springframework.stereotype.Component;

@Component
public interface UserDAO {
    User createUser(String firstName, String lastName, String email, String password);
}
