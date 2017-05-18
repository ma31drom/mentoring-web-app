package com.epam.mentoring.service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.mentoring.config.DaoConfig;

@Configuration
@ComponentScan(basePackages = "com.epam.mentoring.service.impl")
@Import({ DaoConfig.class })
public class ServiceConfig {

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
