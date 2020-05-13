package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class BankAccountDAOImplTest {
    private BankAccountDAOImpl bankAccountDAO;
    String iban = "FR293456789876543456";
    String bic = "PPFRTTSSKR";
    String swift = "45678654";

    @BeforeEach
    private void setUpForTest() throws Exception {
        bankAccountDAO = new BankAccountDAOImpl();
    }

    @Test
    public void createBankAccountTest() {
        BankAccount expectedBankAccount = new BankAccount();
        expectedBankAccount.setIban(iban);
        expectedBankAccount.setSwift(swift);
        expectedBankAccount.setBic(bic);
        expectedBankAccount.setId(0);

        BankAccount calculatedBankAccount = bankAccountDAO.createBankAccount(iban, bic, swift);

        assertEquals(expectedBankAccount.getIban(), calculatedBankAccount.getIban());
        assertEquals(expectedBankAccount.getBic(), calculatedBankAccount.getBic());
        assertEquals(expectedBankAccount.getSwift(), calculatedBankAccount.getSwift());
        assertEquals(expectedBankAccount.getId(), calculatedBankAccount.getId());
        assertNull(calculatedBankAccount.getUser());
        System.out.print("Test passed: " + expectedBankAccount.getIban() + " (expected) = " + calculatedBankAccount.getIban() + " (actual)");
        System.out.print("Test passed: " + expectedBankAccount.getBic() + " (expected) = " + calculatedBankAccount.getBic() + " (actual)");
        System.out.print("Test passed: " + expectedBankAccount.getSwift() + " (expected) = " + calculatedBankAccount.getSwift() + " (actual)");
        System.out.print("Test passed: " + expectedBankAccount.getId() + " (expected) = " + expectedBankAccount.getId() + " (actual)");
        System.out.print("Test passed: " + calculatedBankAccount.getUser() + " (expected) = " + null + " (actual)");
    }
}
