package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.Refill;
import com.axa.softwareacademy.p6.repository.AccountRepository;
import com.axa.softwareacademy.p6.repository.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RefillDAOImpl implements RefillDAO {
    @Autowired
    CreditCardRepository
            creditCardRepository;

    @Autowired
    AccountRepository
            accountRepository;

    @Override
    public Refill createRefill(CreditCard creditCardWhichWillPay, Account accountThatWillBeRefill, float sum) {
        Refill
                newRefill =
                new Refill();
        newRefill.setCreditCard(creditCardWhichWillPay);
        newRefill.setAccount(accountThatWillBeRefill);
        newRefill.setSum(sum);
        return newRefill;
    }
}
