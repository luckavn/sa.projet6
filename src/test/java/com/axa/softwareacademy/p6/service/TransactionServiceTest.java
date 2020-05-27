package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.User;
import com.axa.softwareacademy.p6.repository.AccountRepository;
import com.axa.softwareacademy.p6.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CreateServiceTest {

    @MockBean
    private AccountRepository accountRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private CreateService createService;

    @Test
    public void givenFriend_addUserToMyNetworkForCredit_returnsTheRightRelation() throws Exception
    {
        // GIVEN
        when(userService.getAuthenticatedUser()).thenReturn(u1);
        when(relationRepository.save(any(User.class))).thenReturn(r21);
        // WHEN
        Relation r = relationService.addUserToMyNetworkForCredit(u2.getId());
        // THEN
        assertNotNull(r);
        assertEquals(u2.getId(), r.getUserCredit().getId());
        assertEquals(u1.getId(), r.getUserDebit().getId());
    }
}
