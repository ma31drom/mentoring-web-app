package com.epam.mentoring.service;

import java.util.List;

import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Review;
import com.epam.mentoring.model.User;

public interface ReviewService extends CrudService<Review> {

	List<Review> getReviewsByUser(User user);

	List<Review> getReviewsForFlight(Flight flight);

}
