package com.epam.mentoring.web.controller.html;

import java.util.Optional;

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

@Controller
public class HomeController extends RolesInViewAwareController {

	@Autowired
	AuthenticationTrustResolver authenticationTrustResolver;

	@Autowired
	PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home(@RequestParam(required = false) Optional<String> errorMessage,
			@RequestParam(required = false) Optional<String> messages, ModelMap map) {
		errorMessage.ifPresent(em -> map.addAttribute("errorMessage", em));
		messages.ifPresent(m -> map.addAttribute("messages", m));
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

	@ModelAttribute("homePage")
	public Boolean homePage() {
		return true;
	}

}
