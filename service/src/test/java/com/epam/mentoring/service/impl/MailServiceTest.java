package com.epam.mentoring.service.impl;

import javax.mail.MessagingException;

import org.junit.Test;

public class MailServiceTest {

	@Test(expected = RuntimeException.class)
	public void sendEmailFailedTest() throws MessagingException {
		// Throw messaging exception in reason if misconfigured service
		MailServiceImpl service = new MailServiceImpl();

		service.sendSupportRequest("email", "text");
	}

}
