package com.jwsolutions.pretiustest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PretiusTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(PretiusTestApplication.class, args);
	}
}
