package com.epam.mentoring.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.cfg.AvailableSettings;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource("classpath:dao.properties")
@EnableTransactionManagement
@ComponentScan(basePackages = { "com.epam.mentoring.dao" })
@EnableJpaRepositories("com.epam.mentoring.dao.repository")
public class DaoConfig {
	private static final String PROP_DATABASE_DRIVER = "datasource.driverClassName";
	private static final String PROP_DATABASE_PASSWORD = "datasource.password";
	private static final String PROP_DATABASE_URL = "datasource.url";
	private static final String PROP_DATABASE_USERNAME = "datasource.username";
	private static final String PROP_HIBERNATE_DIALECT = "hibernate.dialect";
	private static final String PROP_HIBERNATE_SHOW_SQL = "hibernate.show.sql";
	private static final String PROP_ENTITYMANAGER_PACKAGES_TO_SCAN = "com.epam.mentoring.model";
	private static final String PROP_HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS = "hibernate.current.session.context.class";
	private static final String PROPERTY_HIBERNATE_HBM2DLL = "hibernate.hbm2ddl.auto";

	@Resource
	private Environment env;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
		dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
		dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
		dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));
		return dataSource;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		entityManagerFactoryBean.setPackagesToScan(PROP_ENTITYMANAGER_PACKAGES_TO_SCAN);
		entityManagerFactoryBean.setJpaProperties(getHibernateProperties());
		return entityManagerFactoryBean;
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory().getObject());
		return transactionManager;
	}

	private Properties getHibernateProperties() {
		Properties properties = new Properties();
		properties.put(AvailableSettings.DIALECT, env.getRequiredProperty(PROP_HIBERNATE_DIALECT));
		properties.put(AvailableSettings.SHOW_SQL, env.getRequiredProperty(PROP_HIBERNATE_SHOW_SQL));
		properties.put(AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
				env.getRequiredProperty(PROP_HIBERNATE_CURRENT_SESSION_CONTEXT_CLASS));
		properties.put(AvailableSettings.ENABLE_LAZY_LOAD_NO_TRANS, true);
		properties.put(AvailableSettings.HBM2DDL_AUTO, env.getRequiredProperty(PROPERTY_HIBERNATE_HBM2DLL));
		return properties;
	}

}
