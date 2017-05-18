package com.epam.mentoring.dao;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import java.util.Date;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.epam.mentoring.config.DaoConfig;
import com.epam.mentoring.dao.repository.CredentialsRepository;

@ContextConfiguration(classes = { DaoConfig.class })
public class TokenRepoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private TokenRepo repo;
	@Autowired
	private CredentialsRepository credRepo;

	@Test
	public void tokenCreateTest() {
		repo.createNewToken(new PersistentRememberMeToken("name0", null, "token0", new Date()));
		repo.createNewToken(new PersistentRememberMeToken("name1", null, "token1", new Date()));
		repo.createNewToken(new PersistentRememberMeToken("name2", null, "token2", new Date()));
		repo.createNewToken(new PersistentRememberMeToken("name3", null, "token3", new Date()));
		repo.createNewToken(new PersistentRememberMeToken("name4", null, "token4", new Date()));
	}

	@Test(dependsOnMethods = { "tokenCreateTest", "tokenUpdateAndGetTest" })
	public void tokenDeleteTest() {
		repo.removeUserTokens("name0");
		repo.removeUserTokens("name1");
		repo.removeUserTokens("name2");
		repo.removeUserTokens("name3");
		repo.removeUserTokens("name4");
	}

	@Test(dependsOnMethods = { "tokenCreateTest" })
	public void tokenUpdateAndGetTest() {
		Stream.of("name0", "name1", "name2", "name3", "name4").map(credRepo::findByUsername)
				.map(cr -> new PersistentRememberMeToken(cr.getUsername(),
						cr.getSeries(),
						cr.getToken() + "00",
						cr.getLastUsed()))
				.forEach(token -> {
					repo.updateToken(token.getSeries(), token.getTokenValue(), token.getDate());
					PersistentRememberMeToken tokenForSeries = repo.getTokenForSeries(token.getSeries());
					assertEquals(token.getTokenValue(), tokenForSeries.getTokenValue());
				});
	}

	@Test
	public void tokenNotExistsGetTest() {
		PersistentRememberMeToken tokenForSeries = repo.getTokenForSeries("some invalid series");
		assertNull(tokenForSeries);
	}

}
