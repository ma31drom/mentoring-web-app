package com.epam.mentoring.web.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.service.UserProfileService;

@Component
public class RoleToUserProfileConverter implements Converter<Object, UserProfile> {

	@Autowired
	UserProfileService userProfileService;

	public UserProfile convert(Object element) {
		Long id = Long.parseLong((String) element);
		return userProfileService.findById(id);
	}

}