package com.axa.softwareacademy.p6.controller;

import com.axa.softwareacademy.p6.PayMyBuddyApplication;
import com.axa.softwareacademy.p6.configuration.H2TestProfileJPAConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = {PayMyBuddyApplication.class, H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    MockMvc mockmvc;

    @Test
    public void getAllUsers() throws Exception {
        this.mockmvc.perform(get("/user/all"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getStatus();
    }

    @Test
    public void addNewUser() throws Exception {
        this.mockmvc.perform(post("/user/add")
                .param("firstname", "John")
                .param("lastname", "Doe")
                .param("email", "john.doe@gmail.com"))
                .andExpect(status().isOk())
                .andReturn().getResponse().getStatus();
    }
}
