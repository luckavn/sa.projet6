package com.axa.softwareacademy.p6;

import com.axa.softwareacademy.p6.configuration.H2TestProfileJPAConfig;
import javafx.application.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(classes = {PayMyBuddyApplication.class, H2TestProfileJPAConfig.class})
@ActiveProfiles("test")
class PayMyBuddyApplicationTests {

    @Test
    void contextLoads() {
    }

}
