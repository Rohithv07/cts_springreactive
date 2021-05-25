package com.examples.spring.webflux.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.spring.webflux.model.Employee;
import com.examples.spring.webflux.service.EmployeeService;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService empService;
	
//	Create Employee 	POST	/employees
//	Get All Employees	GET		/employees
//	Update Employee		PUT		/employees/{id}
//	Delete Employee		DELETE	/employees/{id}
//	Get Employee		GET		/employees/{id}	
	
	@GetMapping
	public Flux<Employee> getAllEmployees() {
		return empService.getAllEmployees();
	}

}
