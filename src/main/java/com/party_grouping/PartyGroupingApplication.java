package com.party_grouping;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PartyGroupingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyGroupingApplication.class, args);
	}

}
