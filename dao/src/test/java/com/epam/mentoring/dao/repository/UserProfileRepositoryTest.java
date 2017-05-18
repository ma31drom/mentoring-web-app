package com.epam.mentoring.dao.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.epam.mentoring.config.DaoConfig;
import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.model.UserProfileType;

@ContextConfiguration(classes = { DaoConfig.class })
public class UserProfileRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserProfileRepository repo;

	@Test()
	public void findByType() {
		UserProfile up = new UserProfile();
		up.setType(UserProfileType.ADMIN.getUserProfileType());

		repo.save(up);

		UserProfile findByType = repo.findByType(UserProfileType.ADMIN.getUserProfileType());

		assertNotNull(findByType);
		assertEquals(findByType.getType(), UserProfileType.ADMIN.getUserProfileType());

	}

}
