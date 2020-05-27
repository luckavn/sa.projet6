package com.axa.softwareacademy.p6.repository;

import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Integer> {
    CreditCard findById(int creditCardId);

    CreditCard findByUser(User user);
}
