package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public interface BankAccountBuilder {
    BankAccount createBankAccount(String iban, String bic, String swift);
}
