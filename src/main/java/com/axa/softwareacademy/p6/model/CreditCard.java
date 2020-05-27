package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull Integer id;
    String cardNumber;
    String expirationDate;
    int cvvNumber;
    @OneToOne
    User user;

    public CreditCard() {}

    public CreditCard(@NotNull int id, String cardNumber, String expirationDate, int cvvNumber) {
        this.id = id;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvvNumber = cvvNumber;
    }

    public CreditCard(String cardNumber, String expirationDate, int cvvNumber) {
        this(0, cardNumber, expirationDate, cvvNumber);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) { this.expirationDate = expirationDate; }

    public int getCvvNumber() { return cvvNumber; }

    public void setCvvNumber(int cvvNumber) { this.cvvNumber = cvvNumber; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

}
