package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.model.UserProfile;

public interface UserProfileService {

	UserProfile findById(Long id);

	UserProfile findByType(String type);

	List<UserProfile> findAll();

	UserProfile save(UserProfile profile);

	void delete(UserProfile profile);
}
