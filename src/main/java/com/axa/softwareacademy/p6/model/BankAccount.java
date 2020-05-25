package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "bank_account")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String iban;
    String bic;
    String swift;
    @OneToOne
    User user;

    public int getId() {
        return id;
    }

    public void setId(int id) { this.id = id; }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BankAccount createBankAccount(String iban, String bic, String swift) {
        BankAccount
                newBankAccount =
                new BankAccount();
        newBankAccount.setBic(bic);
        newBankAccount.setIban(iban);
        newBankAccount.setSwift(swift);
        return newBankAccount;
    }
}
