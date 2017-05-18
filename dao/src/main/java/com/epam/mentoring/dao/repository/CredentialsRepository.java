package com.epam.mentoring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Credentials;

public interface CredentialsRepository extends JpaRepository<Credentials, Long> {

	Credentials findBySeries(String series);

	Credentials findByUsername(String username);
}
