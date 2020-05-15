package com.axa.softwareacademy.p6.dao;

import com.axa.softwareacademy.p6.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@ExtendWith(MockitoExtension.class)
public class UserDAOImplTest {
    private UserDAOImpl userDAO;
    String firstName = "John";
    String lastName = "Doe";
    String email = "john.doe@mail.com";
    String password = "motdepasse";

    @BeforeEach
    private void setUpForTest() throws Exception {
        userDAO = new UserDAOImpl();
    }

    @Test
    public void createUserTest() {
        User expectedUser = new User();
        expectedUser.setFirstName(firstName);
        expectedUser.setLastName(lastName);
        expectedUser.setEmail(email);

        User calculatedUser = userDAO.createUser(firstName, lastName, email, password);
        assertEquals(expectedUser.getFirstName(), calculatedUser.getFirstName());
        assertEquals(expectedUser.getLastName(), calculatedUser.getLastName());
        assertEquals(expectedUser.getEmail(), calculatedUser.getEmail());
        assertNull(calculatedUser.getFriends());
        assertNull(calculatedUser.getBankAccount());
        assertNull(calculatedUser.getCreditCard());
        assertNull(calculatedUser.getId());
        assertNull(calculatedUser.getAccount());
        System.out.print("Test passed: " + expectedUser.getFirstName() + " (expected) = " + calculatedUser.getFirstName() + " (actual)");
        System.out.print("Test passed: " + expectedUser.getLastName() + " (expected) = " + calculatedUser.getLastName() + " (actual)");
        System.out.print("Test passed: " + expectedUser.getEmail() + " (expected) = " + calculatedUser.getEmail() + " (actual)");
        System.out.print("Test passed: " + calculatedUser.getId() + " (expected) = " + null + " (actual)");
        System.out.print("Test passed: " + calculatedUser.getAccount() + " (expected) = " + null + " (actual)");
    }
}

