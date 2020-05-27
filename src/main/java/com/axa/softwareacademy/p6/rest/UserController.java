package com.axa.softwareacademy.p6.rest;

import com.axa.softwareacademy.p6.model.*;
import com.axa.softwareacademy.p6.repository.UserRepository;
import com.axa.softwareacademy.p6.service.ConnectionService;
import com.axa.softwareacademy.p6.service.CreateService;
import com.axa.softwareacademy.p6.service.TransactionService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * This controller is aimed to handle all rest methods that concerns an user
 */
@Controller
@RequestMapping(path = "/user")
public class UserController {
    private static final Logger logger = LogManager.getLogger(UserController.class);
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CreateService createService;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private UserRepository userRepository;

    /**
     * This rest method (GET) is designed to return all users saved in database
     * @return the number of users found
     */
    @GetMapping(path = "/all")
    @ResponseBody
    public String getAllUsers() {
        List<User> usersFound = userRepository.findAll();
        logger.info(usersFound.toString());
        return "Users list" + usersFound.toString();
    }

    /**
     * @param user is the specific user's information given in the request body and needed for method execution
     * @return the response entity "Saved" if method has done well
     * @throws Exception if the user's email already exists in database, cannot create a new account on this address
     */
    @PostMapping(path = "/addUser")
    public ResponseEntity<String> addNewUser(@RequestBody User user) throws Exception {

        if (userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Email already exists. Please provide another one");
        } else {
                User newUser = createService.createUser(user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword());
            }
            logger.info("Add new User - Request OK 200");
            return new ResponseEntity("Saved", HttpStatus.OK);
        }

    /**
     * @param user is the specific user's information given in the request body and needed for method execution
     * @return the user in order to operate all next needed actions
     * @throws Exception if the email address is not recognized as an existing user
     */
    @GetMapping(path = "/connect")
    public User connectUser(@RequestBody User user) throws Exception {
        if(!userRepository.existsByEmail(user.getEmail())) {
            throw new Exception("Please provide correct email address");
        }
            logger.info("User connected");
            return connectionService.connectUserToSession(user.getEmail(), user.getPassword());
    }

    /**
     * @param user is the specific user's information given in the request body and needed for method execution
     * @return the response entity "Saved" if method has done well
     * @throws Exception if in the body the friend's email is not informed
     */
    @PostMapping(path = "/addFriend")
    public ResponseEntity<String> addFriendsListToUser(@RequestBody User user) throws Exception {

        if (!user.getEmail().isEmpty()) {
            createService.createFriendsListAndLinkToUser(user.getId(), user.getEmail());
        } else {
            throw new Exception("Friend's email provided is empty");
        }
        logger.info("Add new friend to user - Request OK 200");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    /**
     * @param id (param) of user that want to add a credit card to his profile
     * @param creditCard (body) information that will be used to create the credit card
     * @return the response entity "Saved" if method has done well
     * @throws Exception if one or many credit card are not correctly informed
     */
    @PostMapping(path = "/addCreditCardToUser")
    public ResponseEntity<String> addCreditCardToUser(@RequestParam(value = "userId") int id, @RequestBody CreditCard creditCard) throws Exception {

        if (!creditCard.getCardNumber().isEmpty() || !creditCard.getExpirationDate().isEmpty() || creditCard.getCvvNumber() == 0) {
            createService.createCreditCardAndLinkToUser(id, creditCard.getCardNumber(), creditCard.getExpirationDate(), creditCard.getCvvNumber());
        } else {
            throw new Exception("Please provide allowed Credit Card information");
        }
        logger.info("Add credit card to user - Request OK 200");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    /**
     * @param id of user that want to refill his Pay My Buddy account from his registered credit card
     * @param refill that will be executed
     * @return the response entity "Saved" if method has done well
     * @throws Exception if the refill sum is equals to 0
     */
    @PostMapping(path = "/addRefillToUserBalance")
    public ResponseEntity<String> addRefillToUserBalance(@RequestParam(value = "userId") int id, @RequestBody Refill refill) throws Exception {
        if (refill.getSum() == 0) {
            throw new Exception("You cannot do a 0€ amount refill");
        } else {
            transactionService.createRefillAndAddToUserAccount(id, refill.getSum());
        }
        logger.info("Add refill to user balance");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    /**
     * @param id of user that want to make a payment to a friend
     * @param friendId of user that will receive the payment from his friend
     * @param payment is the sum
     * @return the response entity "Saved" if method has done well
     * @throws Exception if the payment sum is equals to 0
     */
    @PostMapping(path = "/createPaymentAndUpdateBalances")
    public ResponseEntity<String> createPaymentAndUpdateBalances(@RequestParam(value = "userId") int id, @RequestParam int friendId, @RequestBody Payment payment) throws Exception {

        if (payment.getSum() == 0) {
            throw new Exception("You cannot do a 0€ amount payment");
        } else {
            transactionService.createPaymentAndUpdateBalances(id, friendId, payment.getSum());
        }
        logger.info("Do payment to a friend - Request OK 200");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    /**
     * @param id of user that wants to create a bank account and link it to his profile
     * @param bankAccount are the information of bank account given by user and needed to execute method
     * @return the response entity "Saved" if method has done well
     * @throws Exception if provided bank account's information are not well informed
     */
    @PostMapping(path = "/createBankAccountAndLinkToUser")
    public ResponseEntity<String> createBankAccountAndLinkToUser(@RequestParam (value = "userId") int id, @RequestBody BankAccount bankAccount) throws Exception {

        if (!bankAccount.getIban().isEmpty() || !bankAccount.getBic().isEmpty() || !bankAccount.getSwift().isEmpty()) {
            createService.createBankAccountAndLinkToUser(id, bankAccount.getIban(), bankAccount.getBic(), bankAccount.getSwift());
        } else {
            throw new Exception("Please provide allowed Bank Account information");
        }
        logger.info("Add bank account to user - Request OK 200");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    /**
     * @param id of user that want to proceed to a transfer from his account to his bank account
     * @param transfer is the sum
     * @return the response entity "Saved" if method has done well
     * @throws Exception if the transfer sum is equals to 0
     */
    @PostMapping(path = "/createTransferAndUpdateUserAccount")
    public ResponseEntity<String> createTransferAndUpdateUserAccount(@RequestParam(value = "userId") int id, @RequestBody Transfer transfer) throws Exception {

        if (transfer.getSum() > 0) {
            transactionService.createTransferAndUpdateUserAccount(id, transfer.getSum());
        } else {
            throw new Exception("You cannot do a 0€ amount transfer");
        }
        logger.info("Do transfer from user account to bank account - Request OK 200");
        return new ResponseEntity("Saved", HttpStatus.OK);
    }
}
