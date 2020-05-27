package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public Refill() { }

    public Refill(@NotNull int id, float sum) { this.id = id;this.sum = sum; }

    public Refill(float sum) { this(0, sum); }

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

}
