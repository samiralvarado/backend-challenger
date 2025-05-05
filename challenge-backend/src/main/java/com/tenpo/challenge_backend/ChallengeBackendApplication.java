package com.tenpo.challenge_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class ChallengeBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeBackendApplication.class, args);
	}

}
