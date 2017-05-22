package com.epam.mentoring.service;

import javax.mail.MessagingException;

import com.epam.mentoring.model.User;

public interface MailService {

	void sendAuthRequest(User user) throws MessagingException;

	void sendSupportRequest(User user, String message) throws MessagingException;

	void sendSupportRequest(String email, String message) throws MessagingException;

}
