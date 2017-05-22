package com.epam.mentoring.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.testng.annotations.Test;

import com.epam.mentoring.model.User;
import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.model.UserProfileType;
import com.epam.mentoring.service.UserService;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertTrue;

import java.util.Arrays;
import java.util.HashSet;

public class CustomUserDetailesUnitTest {

	@Test
	public void userDetailesTest() {
		UserService mock = mock(UserService.class);
		User value = new User();
		value.setSsoId("ssoid");
		value.setPassword("pass");
		value.setActivated(true);
		UserProfile up = new UserProfile();
		up.setType(UserProfileType.ADMIN.getUserProfileType());
		value.setUserProfiles(new HashSet<UserProfile>(Arrays.asList(up)));
		when(mock.findBySSO(any())).thenReturn(value);

		CustomUserDetailsService customUserDetailsService = new CustomUserDetailsService(mock);
		UserDetails loadUserByUsername = customUserDetailsService.loadUserByUsername("ssoid");
		verify(mock, times(1)).findBySSO(eq("ssoid"));
		assertTrue(loadUserByUsername.getClass().equals(org.springframework.security.core.userdetails.User.class));
	}

}
