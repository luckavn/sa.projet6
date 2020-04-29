package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class BankAccount {
    @Id
    int id;
    String iban;
    String bic;
    String swift;
    @ManyToOne
    User user;
}
