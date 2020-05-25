package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.Payment;
import com.axa.softwareacademy.p6.model.Refill;
import com.axa.softwareacademy.p6.model.Transfer;
import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.PaymentRepository;
import com.axa.softwareacademy.p6.repository.RefillRepository;
import com.axa.softwareacademy.p6.repository.TransferRepository;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    @Autowired
    Refill refill;
    @Autowired
    Payment payment;
    @Autowired
    Transfer transfer;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefillRepository refillRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TransferRepository transferRepository;

    public Refill createRefillAndAddToUserAccount(int id, float sum) throws Exception {
        User userConcerned = userRepository.findById(id);
        Refill newRefill = refill.createRefill(userConcerned.getCreditCard(), userConcerned.getAccount(), sum);

        if(userRepository.existsById(id)) {
            refillRepository.save(newRefill);
            logger.info("New refill saved and linked to user");
            float userBalance = userConcerned.getAccount().getBalance();
            float newBalance = userBalance + sum;
            userConcerned.getAccount().setBalance(newBalance);
            userRepository.save(userConcerned);
            logger.info("Update account balance for user successful");
        } else {
            throw new Exception("User id doesn't exists");
        }
        logger.info(newRefill);
        return newRefill;
    }

    public Payment createPaymentAndUpdateBalances(int id, int friendId, float sum) throws Exception {
        User userThatWillBeCharged = userRepository.findById(id);
        User userThatWillBeFill = userRepository.findById(friendId);
        Payment newPayment = payment.createPayment(userThatWillBeCharged.getAccount(), userThatWillBeFill.getAccount(), sum);

        if (userThatWillBeCharged.getAccount().getBalance() > newPayment.getSum()) {
            paymentRepository.save(newPayment);
            logger.info("New payment saved and linked to users");
            float userBalance = userThatWillBeCharged.getAccount().getBalance();
            float receiverBalance = userThatWillBeFill.getAccount().getBalance();
            receiverBalance = receiverBalance + newPayment.getSum();
            userBalance = userBalance - newPayment.getSum();
            userThatWillBeFill.getAccount().setBalance(receiverBalance);
            userThatWillBeCharged.getAccount().setBalance(userBalance);
            userRepository.save(userThatWillBeCharged);
            userRepository.save(userThatWillBeFill);
            logger.info("Payment's originator and recipient balances updated");
        } else {
            throw new Exception("You don't have enough money to perform payment");
        }
        logger.info(newPayment);
        return newPayment;
    }

    public Transfer createTransferAndUpdateUserAccount(int id, float sum) throws Exception {
        User userConcerned = userRepository.findById(id);
        Transfer newTransfer = transfer.createTransfer(userConcerned.getAccount(), userConcerned.getBankAccount(), sum);

        if (userConcerned.getAccount().getBalance() > sum) {
            newTransfer.setUser(userConcerned);
            transferRepository.save(newTransfer);
            logger.info("New transfer saved and linked to user");
            float userBalance = userConcerned.getAccount().getBalance();
            float newBalance = userBalance - sum;
            userConcerned.getAccount().setBalance(newBalance);
            userRepository.save(userConcerned);
            logger.info("User account balance updated ");
        } else {
            throw new Exception("You don't have enough money on your balance to perform transfer");
        }
        logger.info(newTransfer);
        return newTransfer;
    }
}
