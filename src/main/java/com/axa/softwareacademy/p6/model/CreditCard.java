package com.axa.softwareacademy.p6.model;

import javax.persistence.*;

@Entity
@Table(name="credit_card")
public class CreditCard {
    @Id
    int id;
    String cardNumber;
    String expirationDate;
    int secretCode;
    @OneToOne
    User user;
}
