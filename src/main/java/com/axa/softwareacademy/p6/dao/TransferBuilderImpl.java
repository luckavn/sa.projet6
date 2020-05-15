package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.Transfer;
import org.springframework.stereotype.Component;

@Component
public class TransferBuilderImpl implements TransferBuilder {
    @Override
    public Transfer createTransfer(Account userAccount, BankAccount userBankAccount, float sum) {
        Transfer
                newTransfer =
                new Transfer();
        newTransfer.setAccount(userAccount);
        newTransfer.setBankAccount(userBankAccount);
        newTransfer.setSum(sum);
        return newTransfer;
    }
}
