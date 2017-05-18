package com.epam.mentoring.service.impl;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.mentoring.dao.repository.ReviewRepository;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.ReviewService;

public class ReviewUnitTest {

	@InjectMocks
	private ReviewService reviewService = new ReviewServiceImpl();

	@Mock
	private ReviewRepository repo;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void getReviewsForFlightTest() {
		Flight flight = new Flight();
		reviewService.getReviewsForFlight(flight);
		verify(repo, times(1)).findByFlight(eq(flight));
	}

	@Test
	public void getReviewsByUserTest() {
		User user = new User();
		reviewService.getReviewsByUser(user);
		verify(repo, times(1)).findByUser(eq(user));
	}
}
