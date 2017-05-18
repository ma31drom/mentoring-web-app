package com.epam.mentoring.dao.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Flight;
import com.epam.mentoring.model.Review;
import com.epam.mentoring.model.User;

public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByUser(User user);

	List<Review> findByFlight(Flight flight);
}
