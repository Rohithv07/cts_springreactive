package com.examples.spring.boot.rest.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.boot.rest.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

	@Autowired
	TestEntityManager entityMgr;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@Test
	public void testCreateEmployee() {
		System.out.println(entityMgr);
		
		Employee paul = new Employee();
		paul.setName("Paul");
		paul.setCountry("Tanzania");
		
//		entityMgr.persist(paul);
		
//		Employee found = entityMgr.find(Employee.class, paul.getId());
		
		empRepo.save(paul);		
		Employee found = entityMgr.find(Employee.class, paul.getId());
		
//		assertEquals(paul.getId(), found.getId());
		
		assertThat(found.getId()).isEqualTo(paul.getId());
		
		assertThat(found).hasFieldOrProperty("name");
	}
}
