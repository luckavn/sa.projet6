package com.axa.softwareacademy.p6.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityFilterAutoConfiguration;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.transaction.annotation.Transactional;

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
public class UserControllerTest {
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
        this.mockmvc.perform(post("/user/addUser")
                .param("firstname", "Paul")
                .param("lastname", "Doe")
                .param("email", "paul.doe@gmail.com")
                .param("password", "motdepasse"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void connectUser() throws Exception {
        this.mockmvc.perform(get("/user/connect")
                .param("email", "marc.doe@gmail.com")
                .param("password", "password"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addCreditCardToUser() throws Exception {
        this.mockmvc.perform(post("/user/addCreditCardToUser")
                .param("userId", "1")
                .param("cardNumber", "1234567898765432")
                .param("expirationDate", "12/24")
                .param("secretCode", "123"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createBankAccountAndLinkToUserTest() throws Exception {
        this.mockmvc.perform(post("/user/createBankAccountAndLinkToUser")
                .param("userId", "1")
                .param("iban", "FR293456789876543456")
                .param("bic", "PPFRTTSSKR")
                .param("swift", "45678654"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addFriendsListToUser() throws Exception {
        this.mockmvc.perform(post("/user/addFriend")
                .param("userId", "1")
                .param("friendEmail", "goeffreyv@gmail.com"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void addRefillToUserBalance() throws Exception {
        this.mockmvc.perform(post("/user/addRefillToUserBalance")
                .param("userId", "1")
                .param("sum", "100"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createPaymentAndUpdateBalances() throws Exception {
        this.mockmvc.perform(post("/user/createPaymentAndUpdateBalances")
                .param("userId", "1")
                .param("friendId", "2")
                .param("sum", "50"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void createTransferAndUpdateUserAccountTest() throws Exception {
        this.mockmvc.perform(post("/user/createTransferAndUpdateUserAccount")
                .param("userId", "1")
                .param("sum", "100"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
