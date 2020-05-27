package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.AccountRepository;
import com.axa.softwareacademy.p6.repository.BankAccountRepository;
import com.axa.softwareacademy.p6.repository.CreditCardRepository;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * This service is only aimed to every method used for creation and saving of element in database as User, Credit Card, Bank Account, ...
 */
@Transactional
@Service
public class CreateService {
    private static final Logger logger = LogManager.getLogger(CreateService.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    BankAccountRepository bankAccountRepository;

    /**
     * @param firstName of user given in front field handle by this method
     * @param lastName of user given in front field handle by this method
     * @param email of user given in front field handle by this method
     * @param password of user given in front field handle by this method
     * @return the user
     */
    public User createUser(String firstName, String lastName, String email, String password) {
        Account newAccountForUser = new Account();
        User newUser = new User(firstName, lastName, email, password);

        logger.info("Account for user saved successfully");

        newUser.setAccount(newAccountForUser);
        logger.info("New user saved successfully");

        newAccountForUser.setUser(newUser);
        newAccountForUser.setCurrency("euro");
        userRepository.save(newUser);

        logger.info("New account for user saved successfully, linked ok");
        return newUser;
    }

    /**
     * @param id of user that will add a friend thanks to this method
     * @param email of friend that will be added to user's friend list
     * @return the user that wants to add a new friend
     * @throws Exception handles that the email to be add as friend is not already one
     */
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

    /**
     * @param id of user that will add a credit card thanks to this method
     * @param cardNumber of user's credit card given in front field handle by this method
     * @param expirationDate of user's credit card given in front field handle by this method
     * @param cvvNumber of user's credit card given in front field handle by this method
     * @return the user that wants to add a credit card
     * @throws Exception if there is already a credit card registered
     */
    public User createCreditCardAndLinkToUser(int id, String cardNumber, String expirationDate, int cvvNumber) throws Exception {
        CreditCard newCreditCard = new CreditCard(cardNumber, expirationDate, cvvNumber);
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

    /**
     * @param id of user that will add a bank account thanks to this method
     * @param iban of user's bank account given in front field handle by this method
     * @param bic of user's bank account given in front field handle by this method
     * @param swift of user's bank account given in front field handle by this method
     * @return the bank account created and linked to the user
     * @throws Exception if there is already a bank account registered
     */
    public BankAccount createBankAccountAndLinkToUser(int id, String iban, String bic, String swift) throws Exception {
        BankAccount newBankAccount = new BankAccount(iban, bic, swift);
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
