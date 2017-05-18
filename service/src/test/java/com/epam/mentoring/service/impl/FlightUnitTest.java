package com.epam.mentoring.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.Date;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.repository.FlightRepository;
import com.epam.mentoring.model.Airport;
import com.epam.mentoring.service.FlightService;

public class FlightUnitTest {

	@InjectMocks
	private FlightService flightService = new FlightServiceImpl();

	@Mock
	private FlightRepository repo;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getFlightsByDateRangeTest() {
		Date before = new Date();
		Date after = new Date();
		flightService.getFlightsByDateRange(after, before);
		verify(repo, times(1)).findByStartDateBetween(eq(after), eq(before));
	}

	@Test
	public void getFlightsByFinishAirportTest() {
		Airport endpoint = new Airport();
		flightService.getFlightsByFinishAirport(endpoint);
		verify(repo, times(1)).findByFinishAirport(eq(endpoint));
	}

	@Test
	public void getFlightsByStartAirportTest() {
		Airport endpoint = new Airport();
		flightService.getFlightsByStartAirport(endpoint);
		verify(repo, times(1)).findByStartAirport(eq(endpoint));
	}
}
