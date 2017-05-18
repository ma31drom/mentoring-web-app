package com.epam.mentoring.service;

import com.epam.mentoring.model.User;

public interface UserService extends CrudService<User> {

	User findBySSO(String sso);

	void deleteUserBySSO(String sso);

	boolean isUserSSOUnique(Long id, String sso);

}
