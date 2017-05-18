package com.epam.mentoring.service.impl;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.epam.mentoring.service.CrudService;

public abstract class AbstractCrudService<T> implements CrudService<T> {

	@Override
	public void save(T obj) {
		getRepo().save(obj);
	}

	@Override
	public void delete(T obj) {
		getRepo().delete(obj);
	}

	@Override
	public void update(T obj) {
		getRepo().save(obj);
	}

	@Override
	public T get(Long id) {
		return getRepo().findOne(id);
	}

	@Override
	public List<T> findAll() {
		return getRepo().findAll();
	}

	protected abstract JpaRepository<T, Long> getRepo();
}
