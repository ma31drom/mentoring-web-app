package com.epam.mentoring.web.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.epam.mentoring.model.Airport;

public class TicketOrderParams {
	@NotNull(message="Starting airport MUST be provided")
	private Airport startAirport;
	@NotNull(message="Finishing airport MUST be provided")
	private Airport endAirport;
	@NotNull(message="Starting date MUST be provided")
	private Date startDate;
	@NotNull(message="Starting date MUST be provided")
	private Date endDate;

	public Airport getStartAirport() {
		return startAirport;
	}

	public void setStartAirport(Airport startAirport) {
		this.startAirport = startAirport;
	}

	public Airport getEndAirport() {
		return endAirport;
	}

	public void setEndAirport(Airport endAirport) {
		this.endAirport = endAirport;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
