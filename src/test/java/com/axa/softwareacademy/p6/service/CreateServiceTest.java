package com.axa.softwareacademy.p6.service;

import com.axa.softwareacademy.p6.model.Account;
import com.axa.softwareacademy.p6.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class CreateServiceTestIT {
    private CreateService createService;

    @BeforeEach
    private void setUpForTest() throws Exception {
        CreateService createService = new CreateService();
    }

   /* @Test
    public void createUserTest() {
        //Arrange
        Account expectedAccount = new Account();
        expectedAccount.setCurrency("euro");
        User expectedUser = new User();
        expectedUser.setFirstName("John");
        expectedUser.setLastName("Doe");
        expectedUser.setEmail("john.doe@email.com");
        expectedUser.setAccount(expectedAccount);

        //Act
        User calculatedUser = createService.createUser("John", "Doe", "john.doe@email.com");

        //Assert
        assertEquals(expectedUser, calculatedUser);
    }*/

    /*@Test
    public void createFriendsListAndLinkToUser() throws Exception {
        //Arrange
        User userWhoWillAddFriend = new User();
        userWhoWillAddFriend.setId(1);
        int idOfFriendToAdd = userWhoWillAddFriend.getId();

        String email = "john.doe@gmail.com";
        User friendToAddInList = new User();
        friendToAddInList.setEmail(email);
        List<User> expectedFriendList = new ArrayList<>();
        expectedFriendList.add(friendToAddInList);

        *//*User existingFriendInList = new User();
        List<User> friendList = new ArrayList<User>();
        List<User> friendListToAdd = new ArrayList<User>();
        friendList.add(existingFriendInList);
        friendListToAdd.add(friendToAddInList);*//*

        //Act
        User calculatedUser = createService.createFriendsListAndLinkToUser(idOfFriendToAdd, email);

        //Assert
        assertEquals(expectedFriendList, calculatedUser.getFriends());
    }*/
}
