package com.examples.spring.webflux.controller;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import com.examples.spring.webflux.model.Employee;
import com.examples.spring.webflux.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerIntegrationTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@MockBean
	EmployeeService employeeService;
	
	@Test
	public void whenGetEmployees_thenCorrectEmployees( ) {	
		
		List<Employee> employees = new ArrayList<>();
		
		Employee emp1 = new Employee(1, "Anand", 30, "Male", false, "Lead", "IT", "Mumbai", "India");
		Employee emp2 = new Employee(2, "Neha", 30, "Female", false, "Analyst", "Research", "Mumbai", "India");
		Employee emp3 = new Employee(3, "Zahoor", 40, "Male", false, "Consultant", "Finance", "Mumbai", "India");	
		employees.add(emp1);
		employees.add(emp2);
		employees.add(emp3);
		
		Mockito.when(employeeService.getAllEmployees()).thenReturn(Flux.fromIterable(employees));
		
		webTestClient
			.get()
			.uri("/employees")
			.exchange()
			.expectStatus().isOk()
            .expectBodyList(Employee.class)
            .hasSize(3);           
			
	}
	
	@Test
	public void givenEmployeeId_whenGetEmployee_thenMatchedEmployee( ) {	
		
		Employee employee = new Employee(1, "Anand", 30, "Male", false, "Lead", "IT", "Mumbai", "India"); 
		
		Mockito.when(employeeService.getEmployee(1)).thenReturn(Mono.just(employee));
		
		webTestClient
			.get()
			.uri("/employees/1")
			.exchange()
			
			.expectStatus().isOk()			
			.expectBody()
//			.isEqualTo(employee);-
			.jsonPath("$.id").isEqualTo(1);
		
//			.consumeWith(response -> Assertions.assertThat(response.getResponseBody().getId()));
//			.value(emp -> System.out.println("Emp ID: " + emp.getId()));
	}	

}
