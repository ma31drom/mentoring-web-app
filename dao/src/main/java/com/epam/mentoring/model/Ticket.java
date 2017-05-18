package com.epam.mentoring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TICKET")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FLIGT_ID", nullable = false, updatable = false)
	private Flight flight;

	@NotNull
	@Column(name = "PLACE_NUMBER")
	private Integer placeNumber;

	@NotNull
	@Column(name = "COST", nullable = false, updatable = false)
	private double cost;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Flight getFlight() {
		return flight;
	}

	public void setFlight(Flight flight) {
		this.flight = flight;
	}

	public Integer getPlaceNumber() {
		return placeNumber;
	}

	public void setPlaceNumber(Integer placeNumber) {
		this.placeNumber = placeNumber;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
