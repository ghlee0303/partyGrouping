package com.party_grouping;

import com.party_grouping.api.CharacterYaml;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@EnableConfigurationProperties(CharacterYaml.class)
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class PartyGroupingApplication {

	public static void main(String[] args) {
		SpringApplication.run(PartyGroupingApplication.class, args);
	}

}
