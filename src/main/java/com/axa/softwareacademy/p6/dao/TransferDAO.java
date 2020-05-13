package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountDAO {
    BankAccount createBankAccount(String iban, String bic, String swift);
}
