package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    CreditCard findById (int creditCardId);
}
