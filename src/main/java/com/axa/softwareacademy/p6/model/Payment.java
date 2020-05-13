package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

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

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public float getCommissionAmount() {
        return commissionAmount;
    }

    public void setCommissionAmount(float commissionAmount) {
        this.commissionAmount = commissionAmount;
    }
}
