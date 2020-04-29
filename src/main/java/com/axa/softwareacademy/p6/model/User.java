package com.axa.softwareacademy.p6.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import java.util.List;

@Entity
public class User {
    @Id
    int id;
    String firstName;
    String lastName;
    @Email String email;
    @OneToMany
    List<User> friends;
    @ManyToOne
    Account account;
    @OneToMany
    List<BankAccount> bankAccounts;
    @OneToMany
    List<CreditCard> creditCards;
}
