package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.CreditCard;
import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.AccountRepository;
import com.axa.softwareacademy.p6.repository.CreditCardRepository;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class CreateServiceTest {
    private User u1;
    private Account a1;
    private CreditCard c1;

    @Autowired
    private ApplicationContext context;

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private CreditCardRepository creditCardRepository;

    private CreateService testedCreateService;

    @BeforeEach
    private void setUp() {
        u1 = new User(0, "Lucas", "Vannier", "lucas@mail.com", "password");
        a1 = new Account(0,100, "euro");
        c1 = new CreditCard(0,"345676543234567876543", "12/23", 123);
        u1.setAccount(a1);
        a1.setUser(u1);
        testedCreateService = context.getBean(CreateService.class);
    }

    @Test
    public void createUserAndSaveAccount() {
        // Arrange
        when(accountRepository.save(any(Account.class))).thenReturn(a1);
        when(userRepository.save(any(User.class))).thenReturn(u1);
        // Act
        User u2 = testedCreateService.createUser("Lucas", "Vannier", "lucas@mail.com", "password");
        // Assert
        assertNotNull(u2);
        assertEquals(u1.getLastName(), u2.getLastName());
        assertEquals(u1.getId(), u2.getId());
    }

    @Test
    public void createCreditCardAndSaveToUser() throws Exception {
        // Arrange
        when(creditCardRepository.save(any(CreditCard.class))).thenReturn(c1);
        when(userRepository.save(any(User.class))).thenReturn(u1);
        when(userRepository.findById(anyInt())).thenReturn(u1);
        // Act
        User u1 = testedCreateService.createCreditCardAndLinkToUser(0,"345676543234567876543", "12/24", 123);
        // Assert
        assertNotNull(c1);
        assertEquals(c1.getId(), u1.getCreditCard().getId());
    }
}
