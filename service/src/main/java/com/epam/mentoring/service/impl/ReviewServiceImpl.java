package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.epam.mentoring.dao.repository.ReviewRepository;
import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Review;
import com.epam.mentoring.model.User;
import com.epam.mentoring.service.ReviewService;

@Service
public class ReviewServiceImpl extends AbstractCrudService<Review> implements ReviewService {

	@Autowired
	private ReviewRepository repo;

	@Override
	public List<Review> getReviewsByUser(User user) {
		return repo.findByUser(user);
	}

	@Override
	public List<Review> getReviewsForFlight(Flight flight) {
		return repo.findByFlight(flight);
	}

	@Override
	protected JpaRepository<Review, Long> getRepo() {
		return repo;
	}

}
