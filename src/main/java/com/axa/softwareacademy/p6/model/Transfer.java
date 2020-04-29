package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Transfer {
    @Id
    int id;
    @ManyToOne
    User user;
    @ManyToOne
    Account account;
    @ManyToOne
    BankAccount bankAccount;
    float sum;
    String status;
    String type;
}
