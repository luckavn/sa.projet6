package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public interface CreditCardDAO {
    CreditCard createCreditCard(String cardNumber, String expirationDate, int secretCode);
}
