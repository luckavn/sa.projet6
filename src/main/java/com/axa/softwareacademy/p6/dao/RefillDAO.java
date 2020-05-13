package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.Refill;
import org.springframework.stereotype.Component;

@Component
public interface RefillDAO {
    public Refill createRefill(CreditCard creditCardWhichWillPay, Account accountThatWillBeRefill, float sum);
}
