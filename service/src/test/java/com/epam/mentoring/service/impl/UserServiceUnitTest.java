package com.epam.mentoring.service.impl;

import org.mockito.ArgumentCaptor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.repository.UserRepository;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.UserService;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

@Test(singleThreaded = true)
public class UserServiceUnitTest {

	private UserRepository repoMock;
	private PasswordEncoder encoMock;
	private UserService service;

	@BeforeMethod
	public void init() {
		repoMock = mock(UserRepository.class);
		encoMock = mock(PasswordEncoder.class);
		when(encoMock.encode(any())).thenReturn("encoded");
		service = new UserServiceImpl(repoMock, encoMock);
	}

	@Test
	public void deleteUserTest() {

		User user = new User();
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

		service.deleteUserBySSO("sso");

		verify(repoMock, times(1)).deleteBySsoId(eq("sso"));
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
