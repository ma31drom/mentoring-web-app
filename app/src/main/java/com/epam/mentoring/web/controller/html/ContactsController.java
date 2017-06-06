package com.epam.mentoring.web.controller.html;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.epam.mentoring.model.User;
import com.epam.mentoring.service.MailService;
import com.epam.mentoring.service.UserService;
import com.epam.mentoring.web.dto.Feedback;

@Controller
public class ContactsController extends RolesInViewAwareController {

	@Autowired
	private MailService mailService;

	@Autowired
	private UserService userService;

	@RequestMapping(path = "/contacts", method = RequestMethod.GET)
	public String contacts(ModelMap map) {
		Feedback attributeValue = new Feedback();
		attributeValue.setMessage("test");
		map.addAttribute("feedback", attributeValue);

		return "contacts";
	}

	@RequestMapping(value = "/contacts", method = RequestMethod.POST)
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
		map.addAttribute("messages", "sent.success");
		return "redirect:/home";
	}

	@ModelAttribute("contactsPage")
	public Boolean contactsPage() {
		return true;
	}

	private User getCurrentUser() {
		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Object principal = authentication.getPrincipal();
		if (principal instanceof UserDetails) {
			return userService.findBySSO(((UserDetails) principal).getUsername());
		} else {
			throw new UnsupportedOperationException("Something went wrong");
		}
	}
}
