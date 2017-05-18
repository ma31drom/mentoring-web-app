package com.epam.mentoring.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "FLIGHT")
public class Flight {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "START_AIRPORT", joinColumns = @JoinColumn(name = "START_AIRPORT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "FLIGhT_ID", referencedColumnName = "ID"))
	private Airport startAirport;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinTable(name = "FINISH_AIRPORT", joinColumns = @JoinColumn(name = "FINISH_AIRPORT_ID", referencedColumnName = "ID"), inverseJoinColumns = @JoinColumn(name = "FLIGHT_ID", referencedColumnName = "ID"))
	private Airport finishAirport;

	@OneToMany(mappedBy = "flight", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Ticket> tickets;

	@Max(value = 10000)
	@Column(name = "DISTANCE", nullable = true)
	private Integer distance;

	@Column(name = "COST", nullable = false)
	private Double cost;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;

	@NotNull
	@Column(name = "AVAILABLE_TICKETS", nullable = false)
	private Integer availableTicketCount;

	@NotNull
	@Column(name = "ALL_TICKETS", nullable = false)
	private Integer fullTicketCount;

	@ManyToMany(mappedBy = "flight", fetch = FetchType.LAZY)
	private List<Review> reviews;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Airport getStartAirport() {
		return startAirport;
	}

	public void setStartAirport(Airport startAirport) {
		this.startAirport = startAirport;
	}

	public Airport getFinishAirport() {
		return finishAirport;
	}

	public void setFinishAirport(Airport finishAirport) {
		this.finishAirport = finishAirport;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public Integer getDistance() {
		return distance;
	}

	public void setDistance(Integer distance) {
		this.distance = distance;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Integer getAvailableTicketCount() {
		return availableTicketCount;
	}

	public void setAvailableTicketCount(Integer availableTicketCount) {
		this.availableTicketCount = availableTicketCount;
	}

	public Integer getFullTicketCount() {
		return fullTicketCount;
	}

	public void setFullTicketCount(Integer fullTicketCount) {
		this.fullTicketCount = fullTicketCount;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

}
