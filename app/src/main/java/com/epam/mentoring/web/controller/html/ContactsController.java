package com.epam.mentoring.web.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ContactsController extends RolesInViewAwareController {

	@RequestMapping(path = "/contacts")
	public String contacts() {
		return "contacts";
	}

	@ModelAttribute("contactsPage")
	public Boolean contactsPage() {
		return true;
	}

}
