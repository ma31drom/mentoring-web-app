package com.epam.mentoring.service.impl;

import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.epam.mentoring.dao.repository.AccountRepository;
import com.epam.mentoring.dao.repository.UserRepository;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.UserService;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class UserServiceUnitJUnitTest {

	private UserRepository repoMock;
	private PasswordEncoder encoMock;
	private AccountRepository accRepoMock;
	private UserService service;

	@Before
	public void init() {
		repoMock = mock(UserRepository.class);
		encoMock = mock(PasswordEncoder.class);
		accRepoMock = mock(AccountRepository.class);
		when(encoMock.encode(any())).thenReturn("encoded");
		service = new UserServiceImpl(repoMock, accRepoMock, encoMock);
	}

	@Test
	public void deleteUserTest() {

		User user = new User();
		user.setSsoId("sso");
		when(repoMock.findBySsoId(any())).thenReturn(user);

		service.delete(user);

		verify(repoMock, times(1)).delete(eq(user));
	}

	@Test
	public void findAllUsersTest() {

		service.findAll();

		verify(repoMock, times(1)).findAll();
	}

	@Test
	public void findByIdTest() {

		service.get(1l);

		verify(repoMock, times(1)).findOne(eq(1l));
	}

	@Test
	public void findBySSOTest() {

		service.findBySSO("sso");

		verify(repoMock, times(1)).findBySsoId(eq("sso"));
	}

	@Test
	public void deleteUserBySSOTest() {
		User value = new User();
		when(repoMock.findBySsoId(any())).thenReturn(value);

		service.deleteUserBySSO("sso");

		verify(repoMock, times(1)).delete(eq(value));
	}

	@Test
	public void isUserSSOUnique() {

		User value2 = new User();
		value2.setId(1l);
		when(repoMock.findBySsoId(any())).thenReturn(value2);
		assertTrue(service.isUserSSOUnique(1l, "sso"));

		verify(repoMock, times(1)).findBySsoId(eq("sso"));
	}

	@Test
	public void saveUserTest() {
		User value2 = new User();
		value2.setId(1l);
		service.save(value2);

		ArgumentCaptor<User> captor = ArgumentCaptor.forClass(User.class);
		verify(repoMock, times(1)).save(captor.capture());
		User value3 = captor.getValue();
		assertEquals(value3.getPassword(), "encoded");
		assertEquals(value3.getId(), Long.valueOf(1));

	}

}
