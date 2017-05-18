package com.epam.mentoring.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "REVIEWS")
public class Review {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "FLIGHT_ID", nullable = false, updatable = false)
	private Flight flight;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USER_ID", nullable = false, updatable = false)
	private User user;

	@Temporal(TemporalType.TIMESTAMP)
	private Date postDate;

	@NotNull
	@Length(min = 4)
	@Column(name = "HEADER")
	private String header;

	@NotNull
	@Length(min = 4)
	@Column(name = "REVIEW")
	private String reviewText;

	@Range(min = 1, max = 5)
	@Column(name = "MARK")
	private Short mark;

	@Column(name = "MODERATED")
	private boolean moderated;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}

	public Short getMark() {
		return mark;
	}

	public void setMark(Short mark) {
		this.mark = mark;
	}

	public boolean isModerated() {
		return moderated;
	}

	public void setModerated(boolean moderated) {
		this.moderated = moderated;
	}

}
