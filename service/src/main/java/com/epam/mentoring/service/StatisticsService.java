package com.epam.mentoring.service;

import java.util.Date;

import com.epam.mentoring.model.Airport;
import com.epam.mentoring.model.Flight;

public interface StatisticsService {

	Double getSummOfSoldTicketInDateRangeForStartingAirport(Date after, Date before, Airport startingAirport);

	String getReviewsForFlightInDateRangeInTextFormat(Flight flight, Date before, Date after);
}
