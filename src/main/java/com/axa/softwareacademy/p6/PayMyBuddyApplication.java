package com.axa.softwareacademy.p6;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.axa.softwareacademy.p6.dao", "com.axa.softwareacademy.p6.rest", "com.axa.softwareacademy.p6.service", "com.axa.softwareacademy.p6.repository"})
public class PayMyBuddyApplication {
	private static final Logger logger = LogManager.getLogger(PayMyBuddyApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
		logger.info("Application launch successful");
	}
}
