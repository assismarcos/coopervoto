package com.disciolli.coopervoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CoopervotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoopervotoApplication.class, args);
	}

}
