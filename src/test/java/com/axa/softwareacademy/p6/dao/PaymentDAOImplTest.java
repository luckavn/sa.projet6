package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class PaymentDAOImplTest {
    private PaymentDAOImpl paymentDAO;
    float sum = 10;

    @BeforeEach
    private void setUpForTest() throws Exception {
        paymentDAO = new PaymentDAOImpl();
    }

    @Test
    public void createPaymentTest() {
        Account accountThatWillBeCharged = new Account();
        Account accountThatWillBeFill = new Account();
        accountThatWillBeCharged.setBalance(100);
        accountThatWillBeCharged.setId(1);
        accountThatWillBeFill.setId(2);

        Payment paymentExpected = new Payment();
        paymentExpected.setSum((float) (sum - 0.5));
        paymentExpected.setAccountRecipient(accountThatWillBeFill);
        paymentExpected.setAccountTransmitter(accountThatWillBeCharged);
        paymentExpected.setCommissionAmount((float) 0.5);

        Payment paymentCalculated = paymentDAO.createPayment(accountThatWillBeCharged, accountThatWillBeFill, sum);

        assertEquals(paymentExpected.getSum(), paymentCalculated.getSum());
        assertEquals(paymentExpected.getCommissionAmount(), paymentCalculated.getCommissionAmount());
        assertEquals(paymentExpected.getAccountRecipient().getId(), paymentCalculated.getAccountRecipient().getId());
        assertEquals(paymentExpected.getAccountTransmitter().getId(), paymentCalculated.getAccountTransmitter().getId());
        System.out.print("Test passed: " + paymentExpected.getSum() + " (expected) = " + paymentCalculated.getSum() + " (actual)");
        System.out.print("Test passed: " + paymentExpected.getCommissionAmount() + " (expected) = " + paymentCalculated.getCommissionAmount() + " (actual)");
        System.out.print("Test passed: " + paymentExpected.getAccountRecipient().getId() + " (expected) = " + paymentCalculated.getAccountRecipient().getId() + " (actual)");
        System.out.print("Test passed: " + paymentExpected.getAccountTransmitter().getId() + " (expected) = " + paymentCalculated.getAccountTransmitter().getId() + " (actual)");
    }

    @Test
    public void calculatePaymentCommissionTest() {
        float commissionAmountExpected = (float) 0.5;

        float commissionAmountCalculated = paymentDAO.calculatePaymentCommission(sum);

        assertEquals(commissionAmountExpected, commissionAmountCalculated);
        System.out.print("Test passed: " + commissionAmountExpected + " (expected) = " + commissionAmountCalculated + " (actual)");

    }
}

