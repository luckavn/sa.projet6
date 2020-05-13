package com.axa.softwareacademy.p6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication(scanBasePackages = {"com.axa.softwareacademy.p6.dao", "com.axa.softwareacademy.p6.rest", "com.axa.softwareacademy.p6.service", "com.axa.softwareacademy.p6.repository"})
public class PayMyBuddyApplication {

	public static void main(String[] args) {
		SpringApplication.run(PayMyBuddyApplication.class, args);
	}
}
