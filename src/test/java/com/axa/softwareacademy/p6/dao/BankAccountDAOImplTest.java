package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.CreditCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class CreditCardDAOImplTest {
    private CreditCardDAOImpl creditCardDAO;
    String cardNumber = "1234567898765432";
    String expirationDate = "12/24";
    int secretCode = 123;

    @BeforeEach
    private void setUpForTest() throws Exception {
        creditCardDAO = new CreditCardDAOImpl();
    }

    @Test
    public void createCreditCardTest() {
        CreditCard expectedCreditCard = new CreditCard();
        expectedCreditCard.setCardNumber(cardNumber);
        expectedCreditCard.setExpirationDate(expirationDate);
        expectedCreditCard.setSecretCode(secretCode);

        CreditCard calculatedCreditCard = creditCardDAO.createCreditCard(cardNumber, expirationDate, secretCode);

        try {
            assertEquals(expectedCreditCard.getCardNumber(), calculatedCreditCard.getCardNumber());
            assertEquals(expectedCreditCard.getExpirationDate(), calculatedCreditCard.getExpirationDate());
            assertEquals(expectedCreditCard.getSecretCode(), calculatedCreditCard.getSecretCode());
            assertNull(calculatedCreditCard.getUser());
            assertEquals(0, expectedCreditCard.getId());
            System.out.print("Test passed: " + expectedCreditCard.getCardNumber() + " (expected) = " + calculatedCreditCard.getCardNumber() + " (actual)");
            System.out.print("Test passed: " + expectedCreditCard.getExpirationDate() + " (expected) = " + calculatedCreditCard.getExpirationDate() + " (actual)");
            System.out.print("Test passed: " + calculatedCreditCard.getUser() + " (expected) = " + null + " (actual)");
            System.out.print("Test passed: " + calculatedCreditCard.getId() + " (expected) = " + 0 + " (actual)");

        } catch (AssertionError ae) {
            throw ae;
        }
    }
}
