package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Component
@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "account")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    int id;
    @OneToOne
    User user;
    float balance;
    String currency;

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public User getUser() {
        return user;
    }

    public void setUser(User user) { this.user = user; }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) { this.balance = balance; }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) { this.currency = currency; }
}
