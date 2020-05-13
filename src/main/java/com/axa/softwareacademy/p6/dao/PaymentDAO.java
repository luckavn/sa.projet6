package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.Payment;
import org.springframework.stereotype.Component;

@Component
public interface PaymentDAO {
    public float calculatePaymentCommission(float sum);

    public Payment createPayment(Account accountThatWillBeCharged, Account accountThatWillBeFill, float sum);
}
