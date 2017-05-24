package com.epam.mentoring.context;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:app.properties")
@Import({ SecurityConfig.class })
public class ApplicationConfig {
}
