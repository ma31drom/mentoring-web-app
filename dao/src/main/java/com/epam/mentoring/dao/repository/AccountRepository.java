package com.epam.mentoring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
}
