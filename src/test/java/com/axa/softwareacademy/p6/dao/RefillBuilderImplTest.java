package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.Refill;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RefillBuilderImplTest {
    private RefillBuilderImpl refillBuilder;
    CreditCard creditCard = new CreditCard();
    Account account = new Account();
    String creditCardNumber = "234567898765434567898765";
    String expirationDate = "06/24";
    int secretCode = 123;
    float sum = 50;

    @BeforeEach
    private void setUpForTest() throws Exception {
        refillBuilder = new RefillBuilderImpl();
    }

    @Test
    public void createRefillTest() {
        Refill refillExpected = new Refill();
        creditCard.setCardNumber(creditCardNumber);
        creditCard.setExpirationDate(expirationDate);
        creditCard.setSecretCode(secretCode);
        creditCard.setId(1);
        refillExpected.setCreditCard(creditCard);
        refillExpected.setSum(sum);
        account.setId(1);
        refillExpected.setAccount(account);

        Refill refillCalculated = refillBuilder.createRefill(creditCard, account, sum);

        assertEquals(refillExpected.getCreditCard().getId(), refillCalculated.getCreditCard().getId());
        assertEquals(refillExpected.getAccount().getId(), refillCalculated.getAccount().getId());
        assertEquals(refillExpected.getCreditCard().getCardNumber(), refillCalculated.getCreditCard().getCardNumber());
        assertEquals(refillExpected.getSum(), refillCalculated.getSum());
        System.out.print("Test passed: " + refillExpected.getCreditCard().getId() + " (expected) = " + refillCalculated.getCreditCard().getId() + " (actual)");
        System.out.print("Test passed: " + refillExpected.getAccount().getId() + " (expected) = " + refillCalculated.getAccount().getId() + " (actual)");
        System.out.print("Test passed: " + refillExpected.getCreditCard().getCardNumber() + " (expected) = " + refillCalculated.getCreditCard().getCardNumber() + " (actual)");
        System.out.print("Test passed: " + refillExpected.getSum() + " (expected) = " + refillCalculated.getSum() + " (actual)");
    }
}

