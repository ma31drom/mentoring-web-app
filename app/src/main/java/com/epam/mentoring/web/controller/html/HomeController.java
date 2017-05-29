package com.epam.mentoring.web.controller.html;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.epam.mentoring.model.User;
import com.epam.mentoring.service.MailService;
import com.epam.mentoring.web.dto.Feedback;
import com.sun.mail.imap.protocol.MailboxInfo;

@Controller
public class HomeController extends RolesInViewAwareController {

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;
	@Autowired
	private MailService mailService;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@RequestParam(required = false) String errorMessage, ModelMap map) {
		if (errorMessage != null) {
			map.addAttribute("errorMessage", errorMessage);
		}
		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		if (isCurrentAuthenticationAnonymous()) {
			return "login";
		} else {
			return "home";
		}
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			persistentTokenBasedRememberMeServices.logout(request, response, auth);
			SecurityContextHolder.getContext().setAuthentication(null);
		}
		return "redirect:/home";
	}

	@RequestMapping(value = "/send-feedback", method = RequestMethod.POST)
	public String feedback(ModelMap map, Feedback feedback) throws MessagingException {
		if (isCurrentAuthenticationAnonymous()) {
			if (feedback.getEmail() == null) {
				map.addAttribute("errorMessage", "Email not given");
				return "contacts";
			}
			mailService.sendSupportRequest(feedback.getEmail(), feedback.getMessage());
		} else {
			mailService.sendSupportRequest(getCurrentUser(), feedback.getMessage());
		}
		return "redirect:/home";
	}

	private User getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return null;
	}

	private boolean isCurrentAuthenticationAnonymous() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authenticationTrustResolver.isAnonymous(authentication);
	}

	@ModelAttribute("homePage")
	public Boolean homePage() {
		return true;
	}

}
