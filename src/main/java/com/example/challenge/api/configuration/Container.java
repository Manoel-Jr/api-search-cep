package com.example.challenge.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Container {
	
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
}
