package com.axa.softwareacademy.p6.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true)
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    private Integer id;
    private String firstName;
    private String lastName;
    @Email
    @Column(unique = true, name = "email")
    private String email;
    @NotNull
    private String password;
    @OneToMany
    private List<User> friends;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "account_id", referencedColumnName="id")
    private Account account;
    @OneToOne
    private BankAccount bankAccount;
    @OneToOne
    private CreditCard creditCard;

    public User() { }

    public User(int id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public User(String firstName, String lastName, String email, String password) {
        this(0, firstName, lastName, email, password);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) { this.id = id; }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public List<User> getFriends() {
        return friends;
    }

    public void setFriends(List<User> friends) { this.friends = friends; }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) { this.account = account; }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public void setBankAccount(BankAccount bankAccount) { this.bankAccount = bankAccount; }

    public CreditCard getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(CreditCard creditCard) { this.creditCard = creditCard; }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) { this.password = password; }

}
