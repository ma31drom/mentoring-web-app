package com.epam.mentoring.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.testng.Assert.assertEquals;

import org.mockito.ArgumentCaptor;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.repository.FlightRepository;
import com.epam.mentoring.dao.repository.TicketRepository;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.TicketService;

public class TicketsUnitTest {

	@InjectMocks
	private TicketService ticketService = new TicketServiceImpl();

	@Mock
	private TicketRepository repo;

	@Mock
	private FlightRepository flightRepo;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReviewsForFlightTest() {
		Flight flight = new Flight();
		ticketService.getTicketsForFlight(flight);
		verify(repo, times(1)).findByFlight(eq(flight));
	}

	@Test
	public void getUserTicketsTest() {
		User user = new User();
		ticketService.getUserTickets(user);
		verify(repo, times(1)).findByUser(eq(user));
	}

	@Test
	public void saveTest() {
		Ticket ticket = new Ticket();
		Flight flight = new Flight();
		ticket.setFlight(flight);
		ticket.getFlight().setAvailableTicketCount(5);
		ticket.getFlight().setFullTicketCount(10);
		ticket.getFlight().setCost(5d);
		ticketService.save(ticket);

		ArgumentCaptor<Ticket> captor = ArgumentCaptor.forClass(Ticket.class);

		verify(repo, times(1)).save(captor.capture());
		verify(flightRepo, times(1)).save(eq(flight));

		Ticket value = captor.getValue();

		assertEquals(value.getFlight().getAvailableTicketCount().intValue(), 4);
		assertEquals(value.getCost(), 5d);
	}

}
