package com.epam.mentoring.web.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TicketsController extends RolesInViewAwareController {

	@RequestMapping(path = "/tickets")
	public String tickets() {
		return "tickets";
	}

	@ModelAttribute("ticketsPage")
	public Boolean ticketsPage() {
		return true;
	}
}
