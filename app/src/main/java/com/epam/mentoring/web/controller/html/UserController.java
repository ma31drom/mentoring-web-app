package com.epam.mentoring.web.controller.html;

import java.util.Arrays;
import java.util.Locale;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.mentoring.model.User;
import com.epam.mentoring.service.MailService;
import com.epam.mentoring.service.UserService;

@Controller
public class UserController extends RolesInViewAwareController {

	@Autowired
	private UserService userService;

	@Autowired
	private MailService mailService;
	@Autowired
	private MessageSource messageSouce;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView users() {
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findAll());
		return mv;
	}

	@RequestMapping(value = "/users/delete-user-{ssoID}", method = RequestMethod.GET)
	public ModelAndView home(@PathVariable String ssoID) {
		userService.deleteUserBySSO(ssoID);
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findAll());
		mv.addObject("messages", Arrays.asList("user.deleted.succesfully"));
		return mv;
	}

	@RequestMapping(value = { "/users/edit-user-{ssoId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

	@RequestMapping(value = { "/users/edit-user-{ssoId}" }, method = RequestMethod.POST)
	public ModelAndView updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String ssoId,
			Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("validationError",
					messageSouce.getMessage("user.validation.error", result.getAllErrors().toArray(), locale));
			model.addAttribute("user", user);
			ModelAndView ret = new ModelAndView("registration");
			ret.getModelMap().addAllAttributes(model);
			return ret;
		}

		userService.update(user);

		model.addAttribute("messages", Arrays.asList("user.edited.succesfully"));
		return users();
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.GET)
	public String registerUser(ModelMap map) {
		map.addAttribute("user", new User());
		return "registration";
	}

	@RequestMapping(value = { "/registration" }, method = RequestMethod.POST)
	public String registerUser(@Valid User user, BindingResult result, ModelMap model, Locale locale) {

		if (result.hasErrors()) {
			model.addAttribute("validationError",
					messageSouce.getMessage("user.validation.error", result.getAllErrors().toArray(), locale));
			return "registration";
		}
		user.setActivated(false);

		boolean anonymous = isCurrentAuthenticationAnonymous();
		if (anonymous) {
			try {
				userService.save(user);
				mailService.sendAuthRequest(user);
			} catch (MessagingException e) {
				userService.delete(user);
			}
			model.addAttribute("messages", Arrays.asList("user.registered.succesfully"));
		} else {
			userService.update(user);
			model.addAttribute("users", userService.findAll());
			model.addAttribute("messages", Arrays.asList("user.created.succesfully"));
		}
		return anonymous ? "home" : "users";
	}

	@RequestMapping(value = { "/confirm-reg-{ssoId}" }, method = RequestMethod.GET)
	public String confirmUser(@PathVariable String ssoId, ModelMap model) {
		User user = userService.findBySSO(ssoId);
		if (user != null) {
			user.setActivated(true);
			userService.update(user);
			model.addAttribute("messages", "user.confirmed");
		}
		return "login";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}

	@ModelAttribute("usersPage")
	public Boolean usersPage() {
		return true;
	}

}
