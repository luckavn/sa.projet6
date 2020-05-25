package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "payment")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne
    Account accountTransmitter;
    @OneToOne
    Account accountRecipient;
    float sum;
    Date paymentDate;
    float commissionAmount;

    public int getId() {
        return id;
    }

    public Account getAccountTransmitter() {
        return accountTransmitter;
    }

    public void setAccountTransmitter(Account accountTransmitter) {
        this.accountTransmitter = accountTransmitter;
    }

    public Account getAccountRecipient() {
        return accountRecipient;
    }

    public void setAccountRecipient(Account accountRecipient) {
        this.accountRecipient = accountRecipient;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public Date getPaymentDate() { Date paymentTime = paymentDate;return paymentTime; }

    public void setPaymentDate(Date paymentDate) {
        if (paymentDate == null) {
            this.paymentDate = null;
        } else
            this.paymentDate = new Date (paymentDate.getTime());
    }

    public float getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(float commissionAmount) {
        this.commissionAmount = commissionAmount;
    }

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

    public float calculatePaymentCommission(float sum) {
        float commissionAmount = (float) (sum * 0.05);
        return commissionAmount;
    }
}
