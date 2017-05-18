package com.epam.mentoring.service;

import java.util.List;

public interface CrudService<T> {

	void delete(T user);

	void update(T user);

	void save(T user);

	T get(Long id);

	List<T> findAll();
}
