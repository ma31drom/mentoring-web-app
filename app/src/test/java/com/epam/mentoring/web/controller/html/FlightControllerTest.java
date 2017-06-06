package com.epam.mentoring.web.controller.html;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.*;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.service.AirportService;
import com.epam.mentoring.service.FlightService;
import com.epam.mentoring.web.dto.TicketOrderParams;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class FlightControllerTest {

	@InjectMocks
	private FlightsController controller;

	@Mock
	private FlightService flightSevice;

	@Mock
	private AirportService airService;

	private Airport endAirport;

	private Airport startAirport;

	private Date endDate;

	private Date startDate;

	@BeforeClass
	public void init() {
		MockitoAnnotations.initMocks(this);

		endAirport = new Airport();
		endAirport.setId(1l);
		startAirport = new Airport();
		startAirport.setId(2l);
		endDate = new Date(2000000);
		startDate = new Date(1000000);

	}

	@BeforeMethod
	public void initMethod() {
		when(flightSevice.findAll()).thenReturn(getFlights());
		when(flightSevice.getFlightsByDateRange(any(), any())).thenReturn(getFlights());
	}

	@Test
	public void getFlightsNoParamsTest() {
		RedirectAttributesModelMap model = new RedirectAttributesModelMap();
		controller.fligtsWithParams(model, null);

		assertNotNull(model.getFlashAttributes().get("ticketOrderParams"));
		assertNotNull(model.getFlashAttributes().get("flights"));
		assertEquals(((List) model.getFlashAttributes().get("flights")).size(), 5);
	}

	@Test
	public void getFlightsWithFullParamsTest() {
		RedirectAttributesModelMap model = new RedirectAttributesModelMap();

		controller.fligtsWithParams(model, getParams());

		TicketOrderParams object = (TicketOrderParams) model.getFlashAttributes().get("ticketOrderParams");
		assertNotNull(object);
		assertEquals(object.getEndAirport(), endAirport);
		assertEquals(object.getStartAirport(), startAirport);
		assertEquals(object.getEndDate(), endDate);
		assertEquals(object.getStartDate(), startDate);

		assertNotNull(model.getFlashAttributes().get("flights"));
		List<Flight> list = (List<Flight>) model.getFlashAttributes().get("flights");
		assertEquals(list.size(), 2);
		list.forEach(obj -> {
			assertEquals(obj.getStartAirport().getId(), startAirport.getId());
			assertEquals(obj.getFinishAirport().getId(), endAirport.getId());
		});
	}

	@Test
	public void getFlightsWithPartialParamsTest() {
		RedirectAttributesModelMap model = new RedirectAttributesModelMap();
		TicketOrderParams params = getParams();
		params.setEndAirport(null);
		controller.fligtsWithParams(model, params);
		TicketOrderParams object = (TicketOrderParams) model.getFlashAttributes().get("ticketOrderParams");
		assertNotNull(object);
		assertNull(object.getEndAirport());
		assertEquals(object.getStartAirport(), startAirport);
		assertEquals(object.getEndDate(), endDate);
		assertEquals(object.getStartDate(), startDate);

		assertNotNull(model.getFlashAttributes().get("flights"));
		List<Flight> list = (List<Flight>) model.getFlashAttributes().get("flights");
		assertEquals(list.size(), 3);
		list.forEach(obj -> {
			assertEquals(obj.getStartAirport().getId(), startAirport.getId());
		});
	}

	@Test
	public void getFlightsWithAnotherPartialParamsTest() {
		RedirectAttributesModelMap model = new RedirectAttributesModelMap();
		TicketOrderParams params = getParams();
		params.setStartAirport(null);
		controller.fligtsWithParams(model, params);

		TicketOrderParams object = (TicketOrderParams) model.getFlashAttributes().get("ticketOrderParams");
		assertNotNull(object);
		assertNull(object.getStartAirport());
		assertEquals(object.getEndAirport(), endAirport);
		assertEquals(object.getEndDate(), endDate);
		assertEquals(object.getStartDate(), startDate);

		assertNotNull(model.getFlashAttributes().get("flights"));
		List<Flight> list = (List<Flight>) model.getFlashAttributes().get("flights");
		assertEquals(list.size(), 3);
		list.forEach(obj -> {
			assertEquals(obj.getFinishAirport().getId(), endAirport.getId());
		});
	}

	private TicketOrderParams getParams() {
		TicketOrderParams ticketOrderParams = new TicketOrderParams();
		ticketOrderParams.setEndAirport(endAirport);
		ticketOrderParams.setStartAirport(startAirport);
		ticketOrderParams.setEndDate(endDate);
		ticketOrderParams.setStartDate(startDate);
		return ticketOrderParams;
	}

	private List<Flight> getFlights() {
		List<Flight> flights = new ArrayList<>();
		flights.add(flight(startAirport.getId(), endAirport.getId(), new Date(1500000), 5));// valid
		flights.add(flight(5l, endAirport.getId(), new Date(1500000), 5));
		flights.add(flight(startAirport.getId(), 5l, new Date(1500000), 5));
		flights.add(flight(startAirport.getId(), endAirport.getId(), new Date(1500000), 0));
		flights.add(flight(startAirport.getId(), endAirport.getId(), new Date(1300000), 5));// valid
		return flights;
	}

	private Flight flight(Long id, Long id2, Date date, Integer availableTicCount) {
		Airport start = new Airport();
		start.setId(id);
		Airport end = new Airport();
		end.setId(id2);
		Flight f = new Flight();
		f.setStartAirport(start);
		f.setFinishAirport(end);
		f.setAvailableTicketCount(availableTicCount);
		f.setStartDate(date);
		return f;
	}

}
