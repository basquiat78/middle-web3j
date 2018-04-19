package com.basquiat.app.config.health;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * HealthCheckController
 * 
 * create by basquiat 2018.04.19
 * 
 */
@RestController
public class HealthCheckController {

	@Autowired
	private HealthCheck healthCheck;
	
	@GetMapping("/healthinfo")
	public Health healthInfo() {
		return healthCheck.health();
	}
			
}
