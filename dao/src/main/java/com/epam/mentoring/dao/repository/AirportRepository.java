package com.epam.mentoring.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {
}
