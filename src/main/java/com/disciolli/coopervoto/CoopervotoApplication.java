package com.disciolli.coopervoto;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoopervotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoopervotoApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
	    return new ModelMapper();
	}
}
