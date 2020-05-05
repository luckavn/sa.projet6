package com.axa.softwareacademy.p6.model;

import javax.persistence.*;

@Entity
@Table(name="account")
public class Account {
    @Id
    int id;
    @OneToOne
    User user;
    float balance;
    String currency;
}
