package com.blockchain.practicum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PracticumApplication {

	public static void main(String[] args) {
		SpringApplication.run(PracticumApplication.class, args);
	}

}
