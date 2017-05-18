package com.epam.mentoring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.epam.mentoring.service.config.ServiceConfig;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
@Import({ ServiceConfig.class })
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private PersistentTokenRepository tokenRepository;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/").access("hasRole('USER') or hasRole('ADMIN')")
				.antMatchers("/newuser/**", "/delete-user-*").access("hasRole('ADMIN')").antMatchers("/edit-user-*")
				.access("hasRole('ADMIN')").and().formLogin().loginPage("/login").loginProcessingUrl("/login")
				.usernameParameter("ssoId").passwordParameter("password").defaultSuccessUrl("/home", true).and()
				.rememberMe().rememberMeParameter("remember-me").tokenRepository(tokenRepository)
				.tokenValiditySeconds(86400).and().csrf().and().exceptionHandling().accessDeniedPage("/Access_Denied");
	}
	/*
	 * @Override protected void configure(HttpSecurity http) throws Exception {
	 * http.csrf().disable().authorizeRequests().antMatchers("/resources/**",
	 * "/*", "WEB-INF/**").permitAll() .anyRequest().permitAll().and();
	 * 
	 * http.formLogin().loginPage("/login").loginProcessingUrl(
	 * "/j_spring_security_check").failureUrl("/login?error=1")
	 * .usernameParameter("j_username").passwordParameter("j_password").
	 * permitAll();
	 * 
	 * http.logout().permitAll().logoutUrl("/j_spring_security_logout").
	 * logoutSuccessUrl("/login?logout") .invalidateHttpSession(true);
	 * 
	 * }
	 */

	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(encoder);
		return authenticationProvider;
	}

	@Bean
	public PersistentTokenBasedRememberMeServices getPersistentTokenBasedRememberMeServices() {
		PersistentTokenBasedRememberMeServices tokenBasedservice = new PersistentTokenBasedRememberMeServices(
				"remember-me", userDetailsService, tokenRepository);
		return tokenBasedservice;
	}

	@Bean
	public AuthenticationTrustResolver getAuthenticationTrustResolver() {
		return new AuthenticationTrustResolverImpl();
	}

}
