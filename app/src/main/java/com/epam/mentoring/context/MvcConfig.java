package com.epam.mentoring.context;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.ui.context.support.ResourceBundleThemeSource;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.theme.CookieThemeResolver;
import org.springframework.web.servlet.theme.ThemeChangeInterceptor;
import org.springframework.web.servlet.view.tiles3.TilesConfigurer;
import com.epam.mentoring.web.converters.RoleToUserProfileConverter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.epam.mentoring.web")
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;

	/**
	 * Configure ViewResolvers to deliver preferred views.
	 */
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		registry.tiles();
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/static/**").addResourceLocations("/static/");
	}

	@Bean
	public ResourceBundleThemeSource themeSource() {
		ResourceBundleThemeSource themeSource = new ResourceBundleThemeSource();
		themeSource.setDefaultEncoding("UTF-8");
		themeSource.setBasenamePrefix("themes.");
		return themeSource;
	}

	@Bean
	public CookieThemeResolver themeResolver() {
		CookieThemeResolver resolver = new CookieThemeResolver();
		resolver.setDefaultThemeName("bright");
		resolver.setCookieName("my-theme-cookie");
		return resolver;
	}

	@Bean
	public ThemeChangeInterceptor themeChangeInterceptor() {
		ThemeChangeInterceptor interceptor = new ThemeChangeInterceptor();
		interceptor.setParamName("theme");
		return interceptor;
	}

	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(roleToUserProfileConverter);
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/i18n/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
		cookieLocaleResolver.setCookieName("localeCookie");
		cookieLocaleResolver.setCookieMaxAge(3600);
		return cookieLocaleResolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(themeChangeInterceptor());
		registry.addInterceptor(localeChangeInteceptor());
	}

	@Bean
	public HandlerInterceptor localeChangeInteceptor() {
		return new LocaleChangeInterceptor();
	}

	@Override
	public void configurePathMatch(PathMatchConfigurer matcher) {
		matcher.setUseRegisteredSuffixPatternMatch(true);
	}

	@Bean
	public TilesConfigurer tilesConfig() {
		TilesConfigurer tilesConfigurer = new TilesConfigurer();
		tilesConfigurer.setDefinitions("/WEB-INF/tiles/definitions.xml");
		return tilesConfigurer;
	}

}
