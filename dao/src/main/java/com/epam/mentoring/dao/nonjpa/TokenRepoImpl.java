package com.epam.mentoring.dao.nonjpa;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.dao.TokenRepo;
import com.epam.mentoring.model.Credentials;
import com.epam.mentoring.dao.repository.CredentialsRepository;

@Repository
@Transactional
public class TokenRepoImpl implements TokenRepo {

	@Autowired
	private CredentialsRepository credentialsRepo;

	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		Credentials credentials = new Credentials();
		credentials.setLastUsed(token.getDate());
		credentials.setSeries(token.getSeries());
		credentials.setToken(token.getTokenValue());
		credentials.setUsername(token.getUsername());
		credentialsRepo.save(credentials);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		Credentials findBySeries = credentialsRepo.findBySeries(seriesId);
		return findBySeries == null ? null
				: new PersistentRememberMeToken(findBySeries.getUsername(),
						findBySeries.getSeries(),
						findBySeries.getToken(),
						findBySeries.getLastUsed());
	}

	@Override
	@Transactional
	public void removeUserTokens(String username) {
		Credentials findByUsername = credentialsRepo.findByUsername(username);
		if (findByUsername != null) {
			credentialsRepo.delete(findByUsername);
		}
	}

	@Override
	public void updateToken(String seriesId, String tokenValue, Date lastUsed) {
		Credentials findBySeries = credentialsRepo.findBySeries(seriesId);
		findBySeries.setLastUsed(lastUsed);
		findBySeries.setToken(tokenValue);
		credentialsRepo.save(findBySeries);
	}

}
