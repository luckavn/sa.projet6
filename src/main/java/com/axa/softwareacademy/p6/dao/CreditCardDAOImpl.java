package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.CreditCard;
import org.springframework.stereotype.Component;

@Component
public class CreditCardDAOImpl implements CreditCardDAO {
    @Override
    public CreditCard createCreditCard(String cardNumber, String expirationDate, int secretCode) {
        CreditCard
                newCreditCard =
                new CreditCard();
        newCreditCard.setCardNumber(cardNumber);
        newCreditCard.setExpirationDate(expirationDate);
        newCreditCard.setSecretCode(secretCode);
        return newCreditCard;
    }
}
