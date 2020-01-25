package com.tokigames.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class TokigamesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokigamesApiApplication.class, args);
	}

}
