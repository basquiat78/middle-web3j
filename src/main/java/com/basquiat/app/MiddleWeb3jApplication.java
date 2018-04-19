package com.basquiat.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class MiddleWeb3jApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiddleWeb3jApplication.class, args);
	}
	
}
