package com.epam.mentoring.service.impl;

import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.epam.mentoring.service.CrudService;

public class CrudServiceUnitTest {

	private static final String TEST = "test";

	private CrudService<String> crudService;

	@Mock
	private JpaRepository<String, Long> repo;

	@BeforeTest
	public void init() {
		MockitoAnnotations.initMocks(this);

		crudService = new AbstractCrudService<String>() {
			@Override
			protected JpaRepository<String, Long> getRepo() {
				return repo;
			}
		};
	}

	@Test
	public void findAllTest() {
		crudService.findAll();
		verify(repo, times(1)).findAll();
	}

	@Test
	public void deleteTest() {
		crudService.delete(TEST);
		verify(repo).delete(eq(TEST));
	}

	@Test
	public void getTest() {
		crudService.get(1l);
		verify(repo).findOne(eq(1l));
	}

	@Test
	public void saveTest() {
		crudService.save(TEST);
		verify(repo).save(eq(TEST));
	}

	@Test
	public void updateTest() {
		crudService.update(TEST + "appendix");
		verify(repo).save(eq(TEST + "appendix"));
	}

}
