package com.epam.mentoring.dao.repository;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

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

@ContextConfiguration(classes = { DaoConfig.class })
public class FlightRepositoryTest extends AbstractTestNGSpringContextTests {

	private static final Date START_DATE_EARLIER = new Date(System.currentTimeMillis() - 10000000);
	private static final Date START_DATE = new Date(System.currentTimeMillis());

	@Autowired
	private FlightRepository flightRepo;

	@Autowired
	private AirportRepository airRepo;

	private Airport start;
	private Airport finish;
	private Flight flight1;
	private Flight flight2;

	@BeforeClass
	public void init() {
		start = new Airport();
		start.setDecsription("Cold1");
		start.setName("North1");
		airRepo.save(start);

		finish = new Airport();
		finish.setDecsription("Hot1");
		finish.setName("South1");
		airRepo.save(finish);

		flight1 = new Flight();
		flight1.setCost(1d);
		flight1.setDistance(1000);
		flight1.setFullTicketCount(5);
		flight1.setAvailableTicketCount(3);
		flight1.setStartAirport(start);
		flight1.setFinishAirport(finish);
		flight1.setStartDate(START_DATE);
		flightRepo.save(flight1);

		flight2 = new Flight();
		flight2.setCost(1d);
		flight2.setDistance(1000);
		flight2.setFullTicketCount(5);
		flight2.setAvailableTicketCount(3);
		flight2.setStartAirport(start);
		flight2.setFinishAirport(finish);
		flight2.setStartDate(START_DATE_EARLIER);
		flightRepo.save(flight2);
	}

	@Test
	public void findByFinishAirportTest() {
		List<Flight> findByFinishAirport = flightRepo.findByFinishAirport(finish);
		assertNotNull(findByFinishAirport);
		assertEquals(findByFinishAirport.size(), 2);
		assertEquals(findByFinishAirport.get(0).getFinishAirport().getId(), finish.getId());
	}

	@Test
	public void findByStartAirportTest() {
		List<Flight> findByStartAirport = flightRepo.findByStartAirport(start);
		assertNotNull(findByStartAirport);
		assertEquals(findByStartAirport.size(), 2);
		assertEquals(findByStartAirport.get(0).getFinishAirport().getId(), finish.getId());
	}

	@Test
	public void findByStartDateBetweenTest() {
		Date after = new Date(START_DATE_EARLIER.getTime() - 5000);
		Date before = new Date(START_DATE_EARLIER.getTime() + 5000);
		List<Flight> findByStartDateBetween = flightRepo.findByStartDateBetween(after, before);
		assertNotNull(findByStartDateBetween);
		assertEquals(findByStartDateBetween.size(), 1);
		assertTrue(findByStartDateBetween.get(0).getStartDate().before(before));
		assertTrue(findByStartDateBetween.get(0).getStartDate().after(after));
	}
}
