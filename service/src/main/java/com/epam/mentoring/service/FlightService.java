package com.epam.mentoring.service;

import java.util.Date;
import java.util.List;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;

public interface FlightService extends CrudService<Flight> {

	List<Flight> getFlightsByDateRange(Date after, Date before);

	List<Flight> getFlightsByStartAirport(Airport endpoint);

	List<Flight> getFlightsByFinishAirport(Airport endpoint);

}
