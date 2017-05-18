package com.epam.mentoring.context;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@PropertySource("classpath:app.properties")
@Import({ SecurityConfig.class })
public class ApplicationConfig {

	@Bean

	// @Profile("init")
	public String pass(@Autowired PasswordEncoder enc) throws IOException {
		String encode = enc.encode("password");
		FileWriter fileWriter = new FileWriter(new File("d:/PASSWORD.txt"));
		fileWriter.write(encode);
		fileWriter.flush();
		fileWriter.close();
		return encode;
	}

}
