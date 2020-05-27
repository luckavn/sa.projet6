package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.*;
import com.axa.softwareacademy.p6.repository.BankAccountRepository;
import com.axa.softwareacademy.p6.repository.TransferRepository;
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
public class TransactionServiceTest {
    private User u1;
    private BankAccount ba1;
    private Account a1;
    private Transfer t1;

    @Autowired
    private ApplicationContext context2;

    @MockBean
    private BankAccountRepository bankAccountRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private TransferRepository transferRepository;

    private TransactionService testedTransactionService;

    @BeforeEach
    private void setUp() {
        u1 = new User(0, "Lucas", "Vannier", "lucas@mail.com", "password");
        a1 = new Account(0,1000,"euro");
        ba1 = new BankAccount(0, "FR762345678909876543252424", "PFRTTSKPR", "34567876543");
        t1 = new Transfer(0,500);
        a1.setUser(u1);
        u1.setBankAccount(ba1);
        u1.setAccount(a1);
        t1.setAccount(a1);
        t1.setBankAccount(ba1);
        testedTransactionService = context2.getBean(TransactionService.class);
    }

    @Test
    public void doTransferFromUserAccountToUserBankAccount() throws Exception {
        // Act
        when(userRepository.save(any(User.class))).thenReturn(u1);
        when(userRepository.findById(anyInt())).thenReturn(u1);
        when(bankAccountRepository.save(any(BankAccount.class))).thenReturn(ba1);
        when(transferRepository.save(any(Transfer.class))).thenReturn(t1);
        // Arrange
        Transfer t2 = testedTransactionService.createTransferAndUpdateUserAccount(0, 500);
        // Assert
        assertNotNull(t2);
        assertEquals(t1.getAccount().getId(), t2.getAccount().getId());
        assertEquals(t1.getBankAccount().getId(), t2.getBankAccount().getId());
    }
}
