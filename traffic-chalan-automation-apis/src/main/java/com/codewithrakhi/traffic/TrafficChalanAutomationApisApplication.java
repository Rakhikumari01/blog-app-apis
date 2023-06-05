package com.codewithrakhi.traffic;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TrafficChalanAutomationApisApplication {

	public static void main(String[] args) {

		SpringApplication.run(TrafficChalanAutomationApisApplication.class, args);
	}

	@Bean
   public ModelMapper modelMapper()
    {
       return new ModelMapper();
    }

}
