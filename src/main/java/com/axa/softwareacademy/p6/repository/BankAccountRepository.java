package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Integer> {
}
