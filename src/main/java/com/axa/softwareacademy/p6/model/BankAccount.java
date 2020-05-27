package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    public BankAccount() { }

    public BankAccount(@NotNull int id, String iban, String bic, String swift) {
        this.id = id;
        this.iban = iban;
        this.bic = bic;
        this.swift = swift;
    }

    public BankAccount(String iban, String bic, String swift) {
        this(0, iban, bic, swift);
    }

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

}
