package com.epam.mentoring.web.controller;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.mentoring.service.UserService;

@Controller
public class UserController extends RolesAwareController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public ModelAndView users() {
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findAll());
		return mv;
	}

	@RequestMapping(value = "/delete-user-{ssoID}", method = RequestMethod.GET)
	public ModelAndView home(@PathVariable String ssoID) {
		userService.deleteUserBySSO(ssoID);
		ModelAndView mv = new ModelAndView("users");
		mv.addObject("users", userService.findAll());
		mv.addObject("messages", Arrays.asList("user.deleted.succesfully"));
		return mv;
	}

}
