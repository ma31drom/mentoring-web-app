package com.epam.mentoring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findBySsoId(String sso);

	@Modifying
	@Transactional
	@Query("delete from User u where u.ssoId = ?1")
	void deleteBySsoId(String sso);

}
