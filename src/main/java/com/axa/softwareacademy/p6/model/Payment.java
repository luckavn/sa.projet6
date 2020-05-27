package com.axa.softwareacademy.p6.model;

import com.axa.softwareacademy.p6.constant.commission;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
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

    public Payment() {}

    public Payment(@NotNull int id, float sum) { this.id = id;this.sum = sum; }

    public Payment(float sum) { this(0, sum); }

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

    public static float calculatePaymentCommission(float sum) {
        float commissionAmount = (float) (sum * commission.fivePourcentCommission);
        return commissionAmount;
    }
}
