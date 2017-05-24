package com.epam.mentoring.web.controller.html;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FlightsController extends RolesInViewAwareController {

	@RequestMapping(path = "/flights")
	public String fligts() {
		return "flights";
	}

	// /fligts/add... for userrole like operator

	@ModelAttribute("flightsPage")
	public Boolean flightsPage() {
		return true;
	}

}
