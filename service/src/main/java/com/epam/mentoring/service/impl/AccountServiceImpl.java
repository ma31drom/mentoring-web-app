package com.epam.mentoring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.repository.AccountRepository;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.service.AccountService;

@Service
public class AccountServiceImpl extends AbstractCrudService<Account> implements AccountService {

	@Autowired
	private AccountRepository repo;

	@Override
	protected JpaRepository<Account, Long> getRepo() {
		return repo;
	}

}
