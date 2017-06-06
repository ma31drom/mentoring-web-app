package com.epam.mentoring.service.impl;

import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Review;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.service.ReviewService;
import com.epam.mentoring.service.StatisticsService;
import com.epam.mentoring.service.TicketService;

@Service
public class StreamedStatisticService implements StatisticsService {

	public static final Logger LOGGER = LoggerFactory.getLogger(StatisticsService.class);
	@Autowired
	private TicketService ticketService;
	@Autowired
	private ReviewService reviewService;

	@Override
	public Double getSummOfSoldTicketInDateRangeForStartingAirport(Date after, Date before, Airport startingAirport) {
		return ticketService.findAll()
				.stream()
				.filter(ticket -> ticket.getFlight().getStartDate().after(after))
				.filter(ticket -> ticket.getFlight().getStartDate().before(before))
				.filter(ticket -> ticket.getFlight().getStartAirport().getId().equals(startingAirport.getId()))
				.peek(ticket -> LOGGER.info("Get summ of sold tickets request for " + ticket + " processed"))
				.mapToDouble(Ticket::getCost)
				.reduce(0d, (summ, value) -> summ + value);
	}

	@Override
	public String getReviewsForFlightInDateRangeInTextFormat(Flight flight, Date before, Date after) {
		return reviewService.findAll()
				.stream()
				.filter(review -> review.getPostDate().before(after))
				.filter(rvw -> rvw.getPostDate().after(before))
				.filter(rvw -> rvw.getFlight().getId().equals(flight.getId()))
				.sorted((r1, r2) -> r1.getId().compareTo(r2.getId()))
				.peek(rvw -> LOGGER.info("Get statistics request for " + rvw + " processed"))
				.map(Review::toString)
				.collect(Collectors.joining("\n", "[Reviews:\n", "]"));
	}

}
