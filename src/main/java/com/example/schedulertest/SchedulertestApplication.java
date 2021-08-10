package com.example.schedulertest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SchedulertestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SchedulertestApplication.class, args);
	}

}
