package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.BankAccount;
import com.axa.softwareacademy.p6.model.Transfer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class TransferDAOImplTest {
    private TransferDAOImpl transferDAO;
    Account account = new Account();
    BankAccount bankAccount = new BankAccount();
    float sum = 10;

    @BeforeEach
    private void setUpForTest() throws Exception {
        transferDAO = new TransferDAOImpl();
    }

    @Test
    public void createTransferTest() {
        Transfer expectedTransfer = new Transfer();
        expectedTransfer.setSum(sum);
        expectedTransfer.setAccount(account);
        expectedTransfer.setBankAccount(bankAccount);
        expectedTransfer.setId(0);

        Transfer calculatedTransfer = transferDAO.createTransfer(account, bankAccount, sum);

        assertEquals(expectedTransfer.getSum(), calculatedTransfer.getSum());
        assertEquals(expectedTransfer.getAccount(), calculatedTransfer.getAccount());
        assertEquals(expectedTransfer.getBankAccount(), calculatedTransfer.getBankAccount());
        assertEquals(expectedTransfer.getId(), calculatedTransfer.getId());
        System.out.print("Test passed: " + expectedTransfer.getSum() + " (expected) = " + calculatedTransfer.getSum() + " (actual)");
        System.out.print("Test passed: " + expectedTransfer.getAccount() + " (expected) = " + calculatedTransfer.getAccount() + " (actual)");
        System.out.print("Test passed: " + expectedTransfer.getBankAccount() + " (expected) = " + calculatedTransfer.getBankAccount() + " (actual)");
        System.out.print("Test passed: " + expectedTransfer.getId() + " (expected) = " + calculatedTransfer.getId() + " (actual)");
    }
}
