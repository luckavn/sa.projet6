package com.axa.softwareacademy.p6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.jdbc.Sql;

/**
 * This class is the main method of application. You can run or debug Pay My Buddy application by running main method
 */
@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.axa.softwareacademy.p6.rest", "com.axa.softwareacademy.p6.service", "com.axa.softwareacademy.p6.repository", "com.axa.softwareacademy.p6.model"})
@PropertySource("classpath:application.properties")
public class PayMyBuddyApplication {
	private static final Logger logger = LogManager.getLogger(PayMyBuddyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
		logger.info("Application launch successful");
	}
}
