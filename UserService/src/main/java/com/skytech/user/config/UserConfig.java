package com.skytech.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class UserConfig {
	
	@Bean
	public RestTemplate restTemplete() {
		return new RestTemplate();
	}

}
