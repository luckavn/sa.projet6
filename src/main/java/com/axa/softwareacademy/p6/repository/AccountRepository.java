package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findById(int accountId);

    Account findByUser(User user);
}
