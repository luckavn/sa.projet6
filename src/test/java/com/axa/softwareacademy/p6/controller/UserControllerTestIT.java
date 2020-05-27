package com.axa.softwareacademy.p6.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

import java.util.ResourceBundle;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@EnableAutoConfiguration(exclude = {SecurityFilterAutoConfiguration.class, SecurityAutoConfiguration.class})
@AutoConfigureMockMvc
@AutoConfigureTestDatabase
@WithMockUser
@Sql(scripts = "classpath:data-h2.sql")
@Transactional
public class UserControllerTestIT {
    ResourceBundle myBundle = ResourceBundle.getBundle("JsonBodyForIt");

    @Autowired
    MockMvc mockmvc;

    @Test
    public void getAllUsers() throws Exception {
        this.mockmvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addNewUser() throws Exception {
        this.mockmvc.perform(post("/user/addUser").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("user.add")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void connectUser() throws Exception {
        this.mockmvc.perform(get("/user/connect").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("user.connect")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCreditCardToUser() throws Exception {
        this.mockmvc.perform(post("/user/addCreditCardToUser").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("creditcard.add"))
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBankAccountAndLinkToUserTest() throws Exception {
        this.mockmvc.perform(post("/user/createBankAccountAndLinkToUser").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("bankaccount.add"))
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addFriendsListToUser() throws Exception {
        this.mockmvc.perform(post("/user/addFriend").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("user.addfriend")))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addRefillToUserBalance() throws Exception {
        this.mockmvc.perform(post("/user/addRefillToUserBalance").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("refill.add"))
                .param("userId", "2"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createPaymentAndUpdateBalances() throws Exception {
        this.mockmvc.perform(post("/user/createPaymentAndUpdateBalances").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("payment.do"))
                .param("userId", "1")
                .param("friendId", "2"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createTransferAndUpdateUserAccountTest() throws Exception {
        this.mockmvc.perform(post("/user/createTransferAndUpdateUserAccount").contentType(MediaType.APPLICATION_JSON).content(myBundle.getString("transfer.do"))
                .param("userId", "1"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
