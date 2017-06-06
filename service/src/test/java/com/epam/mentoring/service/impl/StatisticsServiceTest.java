package com.epam.mentoring.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Review;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.service.ReviewService;
import com.epam.mentoring.service.TicketService;

public class StatisticsServiceTest {

	@InjectMocks
	public StreamedStatisticService service;

	@Mock
	public TicketService ticketService;

	@Mock
	public ReviewService reviewService;

	public Date startDate = new Date(1000000);
	public Date endDate = new Date(2000000);

	@BeforeMethod
	public void init() {
		MockitoAnnotations.initMocks(this);

		when(ticketService.findAll()).thenReturn(getTestTickets());

		when(reviewService.findAll()).thenReturn(getReviews());

	}

	@Test
	public void getReviewsForFlightInDateRangeInTextFormatTest() {
		Flight flight = new Flight();
		flight.setId(1l);
		String text = service.getReviewsForFlightInDateRangeInTextFormat(flight, startDate, endDate);

		assertEquals(text,
				"[Reviews:\nReview [id=1, flight=Flight [id=1, startAirport=null, finishAirport=null, tickets=null, distance=null, cost=null, startDate=null, availableTicketCount=null, fullTicketCount=null, reviews=null], user=null, postDate=Thu Jan 01 03:16:40 MSK 1970, header=1, reviewText=text 1, mark=null, moderated=false]\nReview [id=4, flight=Flight [id=1, startAirport=null, finishAirport=null, tickets=null, distance=null, cost=null, startDate=null, availableTicketCount=null, fullTicketCount=null, reviews=null], user=null, postDate=Thu Jan 01 03:26:40 MSK 1970, header=1, reviewText=text 1, mark=null, moderated=false]\nReview [id=7, flight=Flight [id=1, startAirport=null, finishAirport=null, tickets=null, distance=null, cost=null, startDate=null, availableTicketCount=null, fullTicketCount=null, reviews=null], user=null, postDate=Thu Jan 01 03:25:00 MSK 1970, header=1, reviewText=text 1, mark=null, moderated=false]]");
	}

	@Test
	public void getSummOfSoldTicketInDateRangeForStartingAirport() {
		Airport airport = new Airport();
		airport.setId(3l);
		Double summ = service.getSummOfSoldTicketInDateRangeForStartingAirport(startDate, endDate, airport);

		assertEquals(summ, 13.8d);
	}

	public List<Ticket> getTestTickets() {
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		tickets.add(getTicket(1l, new Date(500000), 3d));
		tickets.add(getTicket(8l, new Date(500000), 3d));
		tickets.add(getTicket(1l, new Date(1200000), 5d));
		tickets.add(getTicket(3l, new Date(3000000), 3d));
		tickets.add(getTicket(3l, new Date(500000), 3d));
		tickets.add(getTicket(3l, new Date(1800000), 4.5d));// valid
		tickets.add(getTicket(1l, new Date(500000), 3d));
		tickets.add(getTicket(3l, new Date(1500000), 9.3d));// valid
		tickets.add(getTicket(1l, new Date(500000), 3d));
		tickets.add(getTicket(3l, new Date(5000000), 7d));
		return tickets;
	}

	public List<Review> getReviews() {
		ArrayList<Review> reviews = new ArrayList<Review>();
		reviews.add(getReview(1l, new Date(1000001)));// valid
		reviews.add(getReview(1l, new Date(9000001)));
		reviews.add(getReview(5l, new Date(1000001)));
		reviews.add(getReview(1l, new Date(1600001)));// valid
		reviews.add(getReview(1l, new Date(900001)));
		reviews.add(getReview(7l, new Date(1000001)));
		reviews.add(getReview(1l, new Date(1500001)));// valid
		return reviews;
	}

	private Long id = 0l;

	private Review getReview(Long fligtId, Date date) {
		Review r = new Review();
		r.setId(++id);
		r.setPostDate(date);
		r.setHeader(fligtId.toString());
		r.setReviewText("text " + fligtId.toString());
		r.setFlight(new Flight());
		r.getFlight().setId(fligtId);
		return r;

	}

	private Ticket getTicket(Long airportId, Date date, Double cost) {
		Ticket t1 = new Ticket();
		t1.setCost(cost);
		Flight f1 = new Flight();
		Airport a1 = new Airport();
		a1.setId(airportId);
		f1.setStartAirport(a1);
		f1.setStartDate(date);
		t1.setFlight(f1);
		return t1;
	}

}
