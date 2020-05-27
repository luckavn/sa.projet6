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
/**
 * This service is only aimed to every method used for users' connection (Sign in / Sign out) to app
 */
public class ConnectionService {
    private static final Logger logger = LogManager.getLogger(ConnectionService.class);
    @Autowired
    UserRepository userRepository;

    /**
     *
     * @param email of user given in front field handle by this method
     * @param password of user given in front field handle by this method
     * @return the user in order to allow him to use app
     */
    public User connectUserToSession(String email, String password) {
        User userToConnect = new User();
        if (userRepository.existsByEmail(email))
            userToConnect = userRepository.findUserByEmail(email);
            if (userToConnect.getPassword().contentEquals(password))
                logger.info("User connection succeeded");
        return userToConnect;
    }
}
