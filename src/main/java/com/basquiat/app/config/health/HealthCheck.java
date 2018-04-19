package com.basquiat.app.config.health;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * 
 * health check
 * 
 * create by basquiat 2018.04.19
 * 
 */
@Component
public class HealthCheck implements HealthIndicator {

	@Override
	public Health health() {
	
		boolean isOk = check();
		
		if(!isOk) {
			return Health.down().withDetail("Error Code", 10000).build();
		}
		
		return Health.up().build();
		
	}
	
	public boolean check() {
		return true;
	}

}
