package com.epam.mentoring.service.impl;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.repository.UserProfileRepository;
import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.service.UserProfileService;
import static org.mockito.Mockito.*;

@Test(singleThreaded = true)
public class UserProfileServiceUnitTest {

	private UserProfileRepository repoMock;
	private UserProfileService service;

	@BeforeMethod
	public void init() {
		repoMock = mock(UserProfileRepository.class);
		service = new UserProfileServiceImpl(repoMock);
	}

	@Test
	public void deleteTest() {

		UserProfile userProfile = new UserProfile();
		service.delete(userProfile);

		verify(repoMock, times(1)).delete(eq(userProfile));
	}

	@Test
	public void findAllTest() {

		service.findAll();

		verify(repoMock, times(1)).findAll();
	}

	@Test
	public void findByIdTest() {

		service.findById(1l);

		verify(repoMock, times(1)).findOne(eq(1l));
	}

	@Test
	public void findByTest() {

		service.findByType("type");

		verify(repoMock, times(1)).findByType(eq("type"));
	}

	@Test
	public void saveTest() {

		UserProfile userProfile = new UserProfile();
		service.save(userProfile);

		verify(repoMock, times(1)).save(eq(userProfile));
	}

}
