package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.repository.UserProfileRepository;
import com.epam.mentoring.model.UserProfile;
import com.epam.mentoring.service.UserProfileService;

@Service
public class UserProfileServiceImpl implements UserProfileService {

	private UserProfileRepository repo;

	@Autowired
	public UserProfileServiceImpl(UserProfileRepository repo) {
		this.repo = repo;
	}

	@Override
	public UserProfile findById(Long id) {
		return repo.findOne(id);
	}

	@Override
	public UserProfile findByType(String type) {
		return repo.findByType(type);
	}

	@Override
	public List<UserProfile> findAll() {
		return repo.findAll();
	}

	@Override
	public UserProfile save(UserProfile profile) {
		return repo.save(profile);
	}

	@Override
	public void delete(UserProfile profile) {
		repo.delete(profile);
	}

}
