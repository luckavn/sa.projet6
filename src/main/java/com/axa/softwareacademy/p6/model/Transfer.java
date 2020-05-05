package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Transfer {
    @Id
    int id;
    @OneToOne
    User user;
    @OneToOne
    Account account;
    @OneToOne
    BankAccount bankAccount;
    float sum;
    String status;
    String type;
}
