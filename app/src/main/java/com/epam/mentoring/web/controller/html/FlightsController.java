package com.epam.mentoring.web.controller.html;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.epam.mentoring.model.Flight;
import com.epam.mentoring.service.AirportService;
import com.epam.mentoring.service.FlightService;
import com.epam.mentoring.web.dto.TicketOrderParams;

@Controller
public class FlightsController extends RolesInViewAwareController {

	@Autowired
	private FlightService flightService;
	@Autowired
	private AirportService airportService;

	@RequestMapping(path = "/flights", method = RequestMethod.GET)
	public String fligts(ModelMap model) {
		model.addAttribute("ticketOrderParams",
				Optional.ofNullable(model.get("ticketOrderParams")).orElse(new TicketOrderParams()));
		model.addAttribute("airports", airportService.findAll());
		return "flights";
	}

	@RequestMapping(path = "/flights/filter", method = RequestMethod.POST)
	public String fligtsWithParams(RedirectAttributes model, TicketOrderParams ticketOrderParams) {

		model.addFlashAttribute("ticketOrderParams",
				Optional.ofNullable(ticketOrderParams).orElse(new TicketOrderParams()));

		Optional<List<Flight>> map = Optional.ofNullable(ticketOrderParams)
				.map(params -> flightService.getFlightsByDateRange(params.getStartDate(), params.getEndDate()));

		model.addFlashAttribute("flights",
				map.map(fligts -> fligts.stream()
						.filter(flight -> (flight.getAvailableTicketCount() > 0))
						.filter(flight -> Optional.ofNullable(ticketOrderParams.getStartAirport())
								.map(air -> flight.getStartAirport().getId().equals(air.getId()))
								.orElse(Boolean.TRUE))
						.filter(flight -> Optional.ofNullable(ticketOrderParams.getEndAirport())
								.map(air -> flight.getFinishAirport().getId().equals(air.getId()))
								.orElse(Boolean.TRUE))
						.collect(Collectors.toList())).orElse(flightService.findAll()));

		model.addFlashAttribute("airports", airportService.findAll());
		return "redirect:/flights";
	}

	// /fligts/add... for userrole like operator

	@ModelAttribute("flightsPage")
	public Boolean flightsPage() {
		return true;
	}

}
