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
import java.util.Date;

@Service
@Transactional
public class TransactionService {
    private static final Logger logger = LogManager.getLogger(TransactionService.class);
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefillRepository refillRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TransferRepository transferRepository;

    /**
     * @param id of user that will do a refill to his Pay my buddy account thanks to this method
     * @param sum is the amount of the refill
     * @return the refill added to user's account
     * @throws Exception if the user doesn't exists
     */
    public Refill createRefillAndAddToUserAccount(int id, float sum) throws Exception {
        User userConcerned = userRepository.findById(id);
        Refill newRefill = new Refill(sum);
        newRefill.setCreditCard(userConcerned.getCreditCard());
        newRefill.setAccount(userConcerned.getAccount());

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

    /**
     * @param id of user that will do a payment to a friend
     * @param friendId of user that will receive the payment
     * @param sum is the amount of the payment that will be decrease of 5% commission
     * @return the payment executed
     * @throws Exception if the transmitter's account balance is not high enough to perform the payment
     */
    public Payment createPaymentAndUpdateBalances(int id, int friendId, float sum) throws Exception {
        //Identify users
        Date date = new Date();
        User userThatWillBeCharged = userRepository.findById(id);
        User userThatWillBeFill = userRepository.findById(friendId);

        //Initialize payment
        Payment newPayment = new Payment(sum);
        newPayment.setAccountTransmitter(userThatWillBeCharged.getAccount());
        newPayment.setAccountRecipient(userThatWillBeFill.getAccount());

        //Calculate commission and apply
        float commission = Payment.calculatePaymentCommission(sum);
        newPayment.setCommissionAmount(commission);

        //Recalculate sum of payment
        sum = sum - commission;
        newPayment.setSum(sum);
        newPayment.setPaymentDate(date);

        //Do treatment
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

    /**
     * @param id of user that will do a transfer to his bank account from his Pay My Buddy account
     * @param sum is the amount of the transfer
     * @return the transfer
     * @throws Exception if the user's account balance is not high enough to perform the transfer
     */
    public Transfer createTransferAndUpdateUserAccount(int id, float sum) throws Exception {
        //Identify users
        User userConcerned = userRepository.findById(id);

        //Initialize transfer
        Transfer newTransfer = new Transfer(sum);
        newTransfer.setAccount(userConcerned.getAccount());
        newTransfer.setBankAccount(userConcerned.getBankAccount());

        //Do treatment
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
