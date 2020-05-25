package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "credit_card")
public class CreditCard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    int id;
    String cardNumber;
    String expirationDate;
    int cvvNumber;
    @OneToOne
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCvvNumber() { return cvvNumber; }

    public void setCvvNumber(int cvvNumber) { this.cvvNumber = cvvNumber; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CreditCard createCreditCard(String cardNumber, String expirationDate, int cvvNumber) {
        CreditCard
                newCreditCard =
                new CreditCard();
        newCreditCard.setCardNumber(cardNumber);
        newCreditCard.setExpirationDate(expirationDate);
        newCreditCard.setCvvNumber(cvvNumber);
        return newCreditCard;
    }
}
