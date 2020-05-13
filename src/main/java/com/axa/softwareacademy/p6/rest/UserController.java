package com.axa.softwareacademy.p6.rest;

import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.UserRepository;
import com.axa.softwareacademy.p6.service.ConnectionService;
import com.axa.softwareacademy.p6.service.CreateService;
import com.axa.softwareacademy.p6.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CreateService createService;
    @Autowired
    private ConnectionService connectionService;
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path = "/all")
    @ResponseBody
    public String getAllUsers() {
        List<User> usersFound = userRepository.findAll();
        return "Users list" + usersFound.toString();
    }

    @PostMapping(path = "/addUser")
    public ResponseEntity<String> addNewUser(@RequestParam(value = "firstname") String firstName, @RequestParam(value = "lastname") String lastName, @RequestParam String email) throws Exception {

        if (userRepository.existsByEmail(email)) {
            throw new Exception("Email already exists. Please provide another one");
        } else {
            try {
                createService.createUser(firstName, lastName, email);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return new ResponseEntity("Saved", HttpStatus.OK);
        }
    }

    @GetMapping(path = "/connect")
    public User connectUser(@RequestParam String email, @RequestParam String password) throws Exception {
        if(!userRepository.existsByEmail(email)) {
            throw new Exception("Please provide correct email address");
        }
            return connectionService.connectUserToSession(email, password);
    }

    @PostMapping(path = "/addFriend")
    public ResponseEntity<String> addFriendsListToUser(@RequestParam(value = "userId") int id, @RequestParam(value = "friendEmail") String email) throws Exception {

        if (!email.isEmpty()) {
            createService.createFriendsListAndLinkToUser(id, email);
        } else {
            throw new Exception("Friend's email provided is empty");
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    @PostMapping(path = "/addCreditCardToUser")
    public ResponseEntity<String> addCreditCardToUser(@RequestParam(value = "userId") int id, @RequestParam String cardNumber, @RequestParam String expirationDate, @RequestParam int secretCode) throws Exception {

        if (!cardNumber.isEmpty() || !expirationDate.isEmpty() || secretCode == 0) {
            createService.createCreditCardAndLinkToUser(id, cardNumber, expirationDate, secretCode);
        } else {
            throw new Exception("Please provide allowed Credit Card information");
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    @PostMapping(path = "/addRefillToUserBalance")
    public ResponseEntity<String> addRefillToUserBalance(@RequestParam(value = "userId") int id, @RequestParam float sum) throws Exception {

        if (sum == 0) {
            throw new Exception("You cannot do a 0€ amount refill");
        } else {
            transactionService.createRefillAndAddToUserAccount(id, sum);
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    @PostMapping(path = "/createPaymentAndUpdateBalances")
    public ResponseEntity<String> createPaymentAndUpdateBalances(@RequestParam(value = "userId") int id, @RequestParam int friendId, @RequestParam float sum) throws Exception {

        if (sum == 0) {
            throw new Exception("You cannot do a 0€ amount payment");
        } else {
            transactionService.createPaymentAndUpdateBalances(id, friendId, sum);
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    @PostMapping(path = "/createBankAccountAndLinkToUser")
    public ResponseEntity<String> createBankAccountAndLinkToUser(@RequestParam(value = "userId") int id, @RequestParam String iban, @RequestParam String bic, @RequestParam String swift) throws Exception {

        if (!iban.isEmpty() || !bic.isEmpty() || !swift.isEmpty()) {
            createService.createBankAccountAndLinkToUser(id, iban, bic, swift);
        } else {
            throw new Exception("Please provide allowed Bank Account information");
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }

    @PostMapping(path = "/createTransferAndUpdateUserAccount")
    public ResponseEntity<String> createTransferAndUpdateUserAccount(@RequestParam(value = "userId") int id, @RequestParam float sum) throws Exception {

        if (sum > 0) {
            transactionService.createTransferAndUpdateUserAccount(id, sum);
        } else {
            throw new Exception("You cannot do a 0€ amount transfer");
        }
        return new ResponseEntity("Saved", HttpStatus.OK);
    }
}
