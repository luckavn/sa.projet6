package com.axa.softwareacademy.p6.model;

import javax.persistence.*;

@Entity
@Table(name="bank_account")
public class BankAccount {
    @Id
    int id;
    String iban;
    String bic;
    String swift;
    @OneToOne
    User user;
}
