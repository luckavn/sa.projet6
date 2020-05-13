package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConnectionService {
    @Autowired
    UserRepository userRepository;

    public User connectUserToSession(String email, String password) {
        User userToConnect = new User();
        userToConnect = userRepository.findUserByEmail(email);
        System.out.println("Connection succeeded");

        //PREVOIR GESTION DU MOT DE PASSE//
        return userToConnect;
    }
}
