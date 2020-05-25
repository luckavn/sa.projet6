package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);
    @Autowired
    UserRepository userRepository;

    public User connectUserToSession(String email, String password) {
        User userToConnect;
        userToConnect = userRepository.findUserByEmail(email);
        logger.info("User connection succeeded");
        //PREVOIR GESTION DU MOT DE PASSE//
        logger.info(userToConnect);
        return userToConnect;
    }
}
