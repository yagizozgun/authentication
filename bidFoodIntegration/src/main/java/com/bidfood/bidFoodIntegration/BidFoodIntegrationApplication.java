package com.bidfood.bidFoodIntegration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
public class BidFoodIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(BidFoodIntegrationApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		return new RestTemplate();
//	}

}
