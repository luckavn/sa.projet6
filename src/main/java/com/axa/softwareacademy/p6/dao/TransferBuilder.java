package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public interface TransferBuilder {
    Transfer createTransfer(Account userAccount, BankAccount userBankAccount, float sum);
}
