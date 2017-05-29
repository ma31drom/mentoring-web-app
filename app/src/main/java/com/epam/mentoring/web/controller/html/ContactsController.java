package com.epam.mentoring.web.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.epam.mentoring.web.dto.Feedback;

@Controller
public class ContactsController extends RolesInViewAwareController {

	@RequestMapping(path = "/contacts")
	public String contacts(ModelMap map) {
		Feedback attributeValue = new Feedback();
		attributeValue.setMessage("test");
		map.addAttribute("feedback", attributeValue);
		
		return "contacts";
	}

	@ModelAttribute("contactsPage")
	public Boolean contactsPage() {
		return true;
	}

}
