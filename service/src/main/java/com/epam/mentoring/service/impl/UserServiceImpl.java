package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.dao.repository.AccountRepository;
import com.epam.mentoring.dao.repository.UserRepository;
import com.epam.mentoring.model.Account;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository repo;
	private AccountRepository accRepo;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository repo, AccountRepository accRepo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
		this.accRepo = accRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public User get(Long id) {
		return repo.findOne(id);
	}

	public User findBySSO(String sso) {
		return repo.findBySsoId(sso);
	}

	public void save(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		repo.save(user);
		if (user.getAccount() == null) {
			Account account = new Account();
			user.setAccount(account);
			account.setBalance(0d);
			account.setUser(user);
			accRepo.save(account);
		}
	}

	public void update(User user) {
		User entity = repo.findOne(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			if (!passwordEncoder.matches(user.getPassword(), entity.getPassword())
					&& !entity.getPassword().equals(user.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}

			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
			entity.setActivated(user.isActivated());
			repo.saveAndFlush(entity);
		}
	}

	public void deleteUserBySSO(String sso) {
		User findBySsoId = repo.findBySsoId(sso);
		delete(findBySsoId);
	}

	public List<User> findAll() {
		return repo.findAll();
	}

	public boolean isUserSSOUnique(Long id, String sso) {
		User user = findBySSO(sso);
		return (user == null || ((id != null) && (user.getId() == id)));
	}

	@Override
	public void delete(User user) {
		accRepo.delete(user.getAccount());
		repo.delete(user);
	}

}