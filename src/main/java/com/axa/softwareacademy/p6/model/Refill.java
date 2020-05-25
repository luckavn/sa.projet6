package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "refill")
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
public class Refill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @OneToOne
    CreditCard creditCard;
    @OneToOne
    Account account;
    float sum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) {
        this.creditCard = creditCard;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public float getSum() {
        return sum;
    }

    public void setSum(float sum) {
        this.sum = sum;
    }

    public Refill createRefill(CreditCard creditCardWhichWillPay, Account accountThatWillBeRefill, float sum) {
        Refill
                newRefill =
                new Refill();
        newRefill.setCreditCard(creditCardWhichWillPay);
        newRefill.setAccount(accountThatWillBeRefill);
        newRefill.setSum(sum);
        return newRefill;
    }
}
