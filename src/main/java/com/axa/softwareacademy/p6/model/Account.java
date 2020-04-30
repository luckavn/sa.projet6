package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="account")
public class Account {
    @Id
    int id;
    @ManyToOne
    User user;
    float balance;
    String currency;
}
