package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.mentoring.dao.repository.UserRepository;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.UserService;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	private UserRepository repo;

	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository repo, PasswordEncoder passwordEncoder) {
		this.repo = repo;
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
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate
	 * update explicitly. Just fetch the entity from db and update it with
	 * proper values within transaction. It will be updated in db once
	 * transaction ends.
	 */
	public void update(User user) {
		User entity = repo.findOne(user.getId());
		if (entity != null) {
			entity.setSsoId(user.getSsoId());
			if (!user.getPassword().equals(entity.getPassword())) {
				entity.setPassword(passwordEncoder.encode(user.getPassword()));
			}
			entity.setFirstName(user.getFirstName());
			entity.setLastName(user.getLastName());
			entity.setEmail(user.getEmail());
			entity.setUserProfiles(user.getUserProfiles());
		}
	}

	public void deleteUserBySSO(String sso) {
		repo.deleteBySsoId(sso);
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
		repo.delete(user);
	}

}