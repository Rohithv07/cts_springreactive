package com.examples.spring.boot.rest.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.examples.spring.boot.rest.model.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeServiceTest {

	Logger log = LoggerFactory.getLogger(EmployeeServiceTest.class);

	@Autowired
	EmployeeService empService;
	
	static Employee emp;
	static List<Employee> employees;

	@BeforeClass
	public static void start() {

//		empService = new EmployeeService();
		employees = new ArrayList<>();

		emp = new Employee();
		emp.setName("Chuma");
		emp.setCountry("Tanzania");
		employees.add(emp);

		emp = new Employee();
		emp.setName("Paul");
		emp.setCountry("Tanzania");
		employees.add(emp);

		emp = new Employee();
		emp.setName("Saravana");
		emp.setCountry("India");
		employees.add(emp);
	}

	@Before
	public void setupTestCase()
	{
		empService.clear();
	}

	@After
	public void cleanupTestCase() {
		empService.clear();
	}

	@Test
	public void shouldAutoIncrementEmpId() {
		empService.add(employees.get(0));
		assertEquals(1, empService.list().size());
		assertEquals(new Integer(1), empService.list().get(0).getId());
	}

	@Test
	public void shouldAddedEmployeeBeEqualToRetrieved() {
		Employee emp = employees.get(0);
		empService.add(emp);
		assertEquals(emp, empService.list().get(0));
	}

	@Test
	public void testListEmployees() {
		empService.add(employees.get(0));
		empService.add(employees.get(1));
		assertEquals(2, empService.list().size());
	}

	@Test
	public void testGetEmployeeById() {
		// Step 1: Add Employee into Employee Service
		Employee source = employees.get(0);
		empService.add(source);

		// Step 2: Retrieve Employee from Employee Service
		Employee target = empService.get(source.getId());

		// Step 3: Compare Added vs Retrieved Employee are equal
		assertEquals(new Integer(1), target.getId());
	}

	@Test
	public void testUpdateEmployee() {
		Employee source = employees.get(1);
		empService.add(source);
		log.debug("Added Employee:: Emp Id: {}, Emp Name: {}", source.getId(), source.getName());

		Employee empForUpdate = empService.get(source.getId());
		empForUpdate.setName("Joel");
		empService.update(empForUpdate);
		log.debug("Updating Employee:: Emp Id: {}, Emp Name: {}", empForUpdate.getId(), empForUpdate.getName());

		Employee updatedEmp = empService.get(1);
		log.debug("Fetch Updated Employee:: Emp Id: {}, Emp Name: {}", updatedEmp.getId(), updatedEmp.getName());
		assertEquals("Joel", updatedEmp.getName());

	}

	@Test
	public void testDeleteEmployee() {
		Employee source = employees.get(1);
		empService.add(source);
		log.debug("Added Employee:: Emp Id: {}, Emp Name: {}", source.getId(), source.getName());

		System.out.println(empService.list());
		
		empService.delete(1);
		log.debug("Deleting Employee:: Emp Id: {}, Emp Name: {}", source.getId(), source.getName());

		assertEquals(0, empService.list().size());

	}

	@AfterClass
	public static void stop() {
		employees.clear();
		employees = null;
	}

}
