package com.epam.mentoring.dao.repository;

import static org.testng.Assert.assertEquals;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.epam.mentoring.config.DaoConfig;
import com.epam.mentoring.model.Credentials;

@ContextConfiguration(classes = { DaoConfig.class })
public class CredentialsRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private CredentialsRepository repo;

	private Credentials credentials1;
	private Credentials credentials2;

	@Test
	public void findBySeriesTest() {
		credentials1 = new Credentials();
		credentials1.setLastUsed(new Date());
		credentials1.setToken("token10");
		credentials1.setUsername("name10");

		credentials2 = new Credentials();
		credentials2.setLastUsed(new Date());
		credentials2.setToken("token20");
		credentials2.setUsername("name20");

		Credentials save = repo.save(credentials1);
		Credentials save2 = repo.save(credentials2);

		Credentials findBySeries = repo.findBySeries(save.getSeries());
		Credentials findBySeries2 = repo.findBySeries(save2.getSeries());

		assertEquals(credentials1.getToken(), findBySeries.getToken());
		assertEquals(credentials1.getUsername(), findBySeries.getUsername());
		assertEquals(credentials2.getToken(), findBySeries2.getToken());
		assertEquals(credentials2.getUsername(), findBySeries2.getUsername());
	}

	@Test(dependsOnMethods = { "findBySeriesTest" })
	public void findByUserNameTest() {

		Credentials findByUsername = repo.findByUsername("name10");
		Credentials findByUsername2 = repo.findByUsername("name20");

		assertEquals("name10", findByUsername.getUsername());
		assertEquals(credentials1.getToken(), findByUsername.getToken());
		
		assertEquals("name20", findByUsername2.getUsername());
		assertEquals(credentials2.getToken(), findByUsername2.getToken());
	}

}
