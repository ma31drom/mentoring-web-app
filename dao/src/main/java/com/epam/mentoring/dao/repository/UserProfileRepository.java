package com.epam.mentoring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.UserProfile;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {

	UserProfile findByType(String type);

}
