package com.epam.mentoring.dao.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import com.epam.mentoring.config.DaoConfig;
import com.epam.mentoring.model.User;

@ContextConfiguration(classes = { DaoConfig.class })
public class UserRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserRepository repo;

	@Test
	public void findBySsoTest() {
		User user1 = new User();
		user1.setEmail("some@email.com");
		user1.setFirstName("First");
		user1.setLastName("Last");
		user1.setPassword("somepass");
		user1.setSsoId("somesso");
		repo.save(user1);
		User user2 = new User();
		user2.setEmail("other@email.com");
		user2.setFirstName("First!");
		user2.setLastName("Last!");
		user2.setPassword("somepass!");
		user2.setSsoId("somesso!");
		repo.save(user2);

		User findBySsoId = repo.findBySsoId("somesso");
		user1.setId(findBySsoId.getId());
		assertEquals(findBySsoId, user1);

		User findBySsoId2 = repo.findBySsoId("somesso!");
		user2.setId(findBySsoId2.getId());
		assertEquals(findBySsoId2, user2);
	}

	@Test(dependsOnMethods = { "findBySsoTest" })
	public void deleteBySsoTest() {
		repo.deleteBySsoId("somesso");
		assertNull(repo.findBySsoId("somesso"));
		repo.deleteBySsoId("somesso!");
		assertNull(repo.findBySsoId("somesso!"));
	}

}
