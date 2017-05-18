package com.epam.mentoring.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.model.User;
import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.service.UserService;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private UserService userService;

	@Autowired
	public CustomUserDetailsService(UserService userService) {
		this.userService = userService;
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String ssoId) throws UsernameNotFoundException {
		User user = userService.findBySSO(ssoId);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		return new org.springframework.security.core.userdetails.User(user.getSsoId(),
				user.getPassword(),
				true,
				true,
				true,
				true,
				getGrantedAuthorities(user));
	}

	private List<GrantedAuthority> getGrantedAuthorities(User user) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

		for (UserProfile userProfile : user.getUserProfiles()) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + userProfile.getType()));
		}
		return authorities;
	}

}
