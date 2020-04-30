package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="credit_card")
public class CreditCard {
    @Id
    int id;
    String cardNumber;
    String expirationDate;
    int secretCode;
    @ManyToOne
    User user;
}
