package com.epam.mentoring.dao.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.epam.mentoring.config.DaoConfig;
import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Ticket;
import com.epam.mentoring.model.User;

@ContextConfiguration(classes = { DaoConfig.class })
public class TicketRepositoryTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private AirportRepository airRepo;

	@Autowired
	private TicketRepository ticketRepository;

	private Flight flight;
	private Ticket ticket;
	private User user;

	@BeforeClass
	public void init() {
		user = new User();
		user.setEmail("some1@email.com");
		user.setFirstName("First123");
		user.setLastName("Last123");
		user.setPassword("somepass123");
		user.setSsoId("somesso123");
		userRepo.save(user);
		Airport start = new Airport();
		start.setDecsription("Cold");
		start.setName("North");
		airRepo.save(start);
		Airport finish = new Airport();
		finish.setDecsription("Hot");
		finish.setName("South");
		airRepo.save(finish);

		flight = new Flight();
		flight.setCost(1d);
		flight.setDistance(1000);
		flight.setFullTicketCount(5);
		flight.setAvailableTicketCount(3);
		flight.setStartAirport(start);
		flight.setFinishAirport(finish);
		flight.setTickets(Arrays.asList(ticket));
		flight.setStartDate(new Date());
		flightRepo.save(flight);

		ticket = new Ticket();
		ticket.setUser(user);
		ticket.setCost(1d);
		ticket.setFlight(flight);
		ticketRepository.save(ticket);

	}

	@Test
	public void findByFlightTest() {

		List<Ticket> findByFlight = ticketRepository.findByFlight(flight);
		assertNotNull(findByFlight);
		assertEquals(findByFlight.size(), 1);
		assertEquals(findByFlight.get(0).getFlight().getId(), flight.getId());
	}

	@Test
	public void findByUserTest() {
		List<Ticket> findByUser = ticketRepository.findByUser(user);
		assertNotNull(findByUser);
		assertEquals(findByUser.size(), 1);
		assertEquals(findByUser.get(0).getUser().getId(), user.getId());
	}

}
