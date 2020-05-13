package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.PayMyBuddyApplication;
import com.axa.softwareacademy.p6.dao.*;
import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CreateService {
    private static final Logger logger = LogManager.getLogger(CreateService.class);
    @Autowired
    UserDAO userDAO;
    @Autowired
    CreditCardDAO creditCardDAO;
    @Autowired
    BankAccountDAO bankAccountDAO;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;


    public User createUser(String firstName, String lastName, String email) {
        Account newAccountForUser = new Account();
        newAccountForUser.setCurrency("euro");
        accountRepository.save(newAccountForUser);

        User newUser = userDAO.createUser(firstName, lastName, email);
        newUser.setAccount(newAccountForUser);
        userRepository.save(newUser);
        logger.info("New user saved successfully");

        newAccountForUser.setUser(newUser);
        accountRepository.save(newAccountForUser);
        logger.info("New account for user saved successfully, linked ok");
        logger.info(newUser);
        return newUser;
    }

    public User createFriendsListAndLinkToUser(int id, String email) throws Exception {
        List<User> newFriend;
        List<User> existingFriends;
        User userWhoWillAddFriend;
        User friendToAdd;
        friendToAdd = userRepository.findUserByEmail(email);
        userWhoWillAddFriend = userRepository.findById(id);
        existingFriends = userWhoWillAddFriend.getFriends();

        if (!userWhoWillAddFriend.getFriends().contains(friendToAdd)) {
            newFriend = userRepository.findByEmail(email);
            existingFriends.addAll(newFriend);
            userWhoWillAddFriend.setFriends(existingFriends);
            userRepository.save(userWhoWillAddFriend);
            logger.info("New friend for user saved successfully");
        } else {
            throw new Exception("Is already your friend");
        }
        logger.info(userWhoWillAddFriend);
        return userWhoWillAddFriend;
    }

    public User createCreditCardAndLinkToUser(int id, String cardNumber, String expirationDate, int secretCode) throws Exception {
        CreditCard newCreditCard = creditCardDAO.createCreditCard(cardNumber, expirationDate, secretCode);
        User userWhoWillAddCreditCard = userRepository.findById(id);

        if (userWhoWillAddCreditCard.getCreditCard() == null) {
            creditCardRepository.save(newCreditCard);
            userWhoWillAddCreditCard.setCreditCard(newCreditCard);
            userRepository.save(userWhoWillAddCreditCard);
            logger.info("Credit card linked to user");
            newCreditCard.setUser(userWhoWillAddCreditCard);
            creditCardRepository.save(newCreditCard);
            logger.info("Credit card saved");
        } else {
            throw new Exception("You already have a credit card informed for your account");
        }
        logger.info(userWhoWillAddCreditCard);
        return userWhoWillAddCreditCard;
    }

    public BankAccount createBankAccountAndLinkToUser(int id, String iban, String bic, String swift) throws Exception {
        BankAccount newBankAccount = bankAccountDAO.createBankAccount(iban, bic, swift);
        User userWhoWillAddBankAccount = userRepository.findById(id);
        newBankAccount.setUser(userWhoWillAddBankAccount);

        if (userWhoWillAddBankAccount.getBankAccount() == null) {
            bankAccountRepository.save(newBankAccount);
            logger.info("Bank Account saved");
            userWhoWillAddBankAccount.setBankAccount(newBankAccount);
            userRepository.save(userWhoWillAddBankAccount);
            logger.info("Bank Account linked to user");
        } else {
            throw new Exception("You already have a bank account informed for your account");
        }
        logger.info(newBankAccount);
        return newBankAccount;
    }
}
