package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.Payment;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class PaymentBuilderImpl implements PaymentBuilder {
    @Override
    public Payment createPayment(Account accountThatWillBeCharged, Account accountThatWillBeFill, float sum) {
        Payment newPayment = new Payment();
        Date date = new Date();
        newPayment.setAccountTransmitter(accountThatWillBeCharged);
        newPayment.setAccountRecipient(accountThatWillBeFill);
        float commissionAmount = calculatePaymentCommission(sum);
        newPayment.setCommissionAmount(commissionAmount);
        sum = sum - commissionAmount;
        newPayment.setSum(sum);
        newPayment.setPaymentDate(date);
        return newPayment;
    }

    @Override
    public float calculatePaymentCommission(float sum) {
        float commissionAmount = (float) (sum * 0.05);
        return commissionAmount;
    }
}
