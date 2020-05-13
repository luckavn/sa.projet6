package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.BankAccount;
import org.springframework.stereotype.Component;

@Component
public class BankAccountDAOImpl implements BankAccountDAO {
    @Override
    public BankAccount createBankAccount(String iban, String bic, String swift) {
        BankAccount
                newBankAccount =
                new BankAccount();
        newBankAccount.setBic(bic);
        newBankAccount.setIban(iban);
        newBankAccount.setSwift(swift);
        return newBankAccount;
    }
}
