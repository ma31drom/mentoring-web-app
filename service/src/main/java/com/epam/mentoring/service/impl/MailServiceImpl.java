package com.epam.mentoring.service.impl;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.epam.mentoring.model.User;
import com.epam.mentoring.service.MailService;

@Service
public class MailServiceImpl implements MailService, InitializingBean {

	@Value("${email.account}")
	private String account;

	@Value("${email.support}")
	private String support;

	@Value("${email.password}")
	private String password;

	@Value("${mail.smtp.auth}")
	private String smtpAuth;

	@Value("${mail.smtp.starttls.enable}")
	private String tlsEnable;

	@Value("${mail.smtp.host}")
	private String host;

	@Value("${mail.smtp.port}")
	private String port;

	private ExecutorService executor;

	private static final String AUTH = "mail.smtp.auth";
	private static final String TLS = "mail.smtp.starttls.enable";
	private static final String HOST = "mail.smtp.host";
	private static final String PORT = "mail.smtp.port";

	private Properties props;

	public MailServiceImpl() {
		executor = Executors.newSingleThreadExecutor();
		Runtime.getRuntime().addShutdownHook(new Thread(() -> {
			executor.shutdown();
		}));
	}

	@Override
	public void sendAuthRequest(User user) {
		executor.execute(() -> {
			MimeMessage message = getMailSession();
			try {
				message.setFrom("noreplay@naumovichMentoring");
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(user.getEmail()));
				message.setSubject("Registration confirmation");

				message.setText(
						"Please, use next link to confirm your email:\n\n <a href='http://localhost:8181/app/confirm-reg-"
								+ user.getSsoId() + "'>Confirm link</a>",
						"UTF-8",
						"html");
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException("Unnable to send message", e);
			}

		});
	}

	@Override
	public void sendSupportRequest(User user, String messageText) {
		executor.execute(() -> {
			MimeMessage message = getMailSession();
			try {
				message.setFrom(user.getEmail());
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(support));
				message.setSubject("SupportRequest");
				message.setText("From " + user.getSsoId() + ", request:/n" + messageText);
				Transport.send(message);
			} catch (MessagingException e) {
				throw new RuntimeException("Unnable to send message", e);
			}
		});
	}

	@Override
	public void sendSupportRequest(String email, String messageText) {
		MimeMessage message = getMailSession();
		try {
			message.setFrom(email);
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(support));
			message.setSubject("SupportRequest");
			message.setText("From anonumous user, request:/n" + messageText);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException("Unnable to send message", e);
		}
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		props = new Properties();
		props.put(AUTH, smtpAuth);
		props.put(TLS, tlsEnable);
		props.put(HOST, host);
		props.put(PORT, port);
	}

	private MimeMessage getMailSession() {
		Session instance = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(account, password);
			}
		});
		return new MimeMessage(instance);
	}

}
