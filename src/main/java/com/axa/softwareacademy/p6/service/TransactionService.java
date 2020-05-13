package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.dao.PaymentDAO;
import com.axa.softwareacademy.p6.dao.RefillDAO;
import com.axa.softwareacademy.p6.dao.TransferDAO;
import com.axa.softwareacademy.p6.model.Payment;
import com.axa.softwareacademy.p6.model.Refill;
import com.axa.softwareacademy.p6.model.Transfer;
import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.PaymentRepository;
import com.axa.softwareacademy.p6.repository.RefillRepository;
import com.axa.softwareacademy.p6.repository.TransferRepository;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class TransactionService {
    @Autowired
    RefillDAO refillDAO;
    @Autowired
    PaymentDAO paymentDAO;
    @Autowired
    TransferDAO transferDAO;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RefillRepository refillRepository;
    @Autowired
    PaymentRepository paymentRepository;
    @Autowired
    TransferRepository transferRepository;

    public Refill createRefillAndAddToUserAccount(int id, float sum) throws Exception {
        User
                userConcerned = userRepository.findById(id);
        Refill newRefill = refillDAO.createRefill(userConcerned.getCreditCard(), userConcerned.getAccount(), sum);

        if(userRepository.existsById(id)) {
            refillRepository.save(newRefill);
            float userBalance = userConcerned.getAccount().getBalance();
            float newBalance = userBalance + sum;
            userConcerned.getAccount().setBalance(newBalance);
            userRepository.save(userConcerned);
        } else {
            throw new Exception("User id doesn't exists");
        }
        return newRefill;
    }

    public Payment createPaymentAndUpdateBalances(int id, int friendId, float sum) throws Exception {
        User userThatWillBeCharged = userRepository.findById(id);
        User userThatWillBeFill = userRepository.findById(friendId);
        Payment newPayment = paymentDAO.createPayment(userThatWillBeCharged.getAccount(), userThatWillBeFill.getAccount(), sum);

        if (userThatWillBeCharged.getAccount().getBalance() > newPayment.getSum()) {
            paymentRepository.save(newPayment);
            float userBalance = userThatWillBeCharged.getAccount().getBalance();
            float receiverBalance = userThatWillBeFill.getAccount().getBalance();
            receiverBalance = receiverBalance + newPayment.getSum();
            userBalance = userBalance - newPayment.getSum();
            userThatWillBeFill.getAccount().setBalance(receiverBalance);
            userThatWillBeCharged.getAccount().setBalance(userBalance);
            userRepository.save(userThatWillBeCharged);
            userRepository.save(userThatWillBeFill);
        } else {
            throw new Exception("You don't have enough money to perform payment");
        }
        return newPayment;
    }

    public Transfer createTransferAndUpdateUserAccount(int id, float sum) throws Exception {
        User userConcerned = userRepository.findById(id);
        Transfer newTransfer = transferDAO.createTransfer(userConcerned.getAccount(), userConcerned.getBankAccount(), sum);

        if (userConcerned.getAccount().getBalance() > sum) {
            newTransfer.setUser(userConcerned);
            transferRepository.save(newTransfer);
            float userBalance = userConcerned.getAccount().getBalance();
            float newBalance = userBalance - sum;
            userConcerned.getAccount().setBalance(newBalance);
            userRepository.save(userConcerned);
        } else {
            throw new Exception("You don't have enough money on your balance to perform transfer");
        }
        return newTransfer;
    }
}
