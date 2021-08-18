package com.examples.spring.security.oauth.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.spring.security.oauth.model.Employee;

@RestController
public class EmployeeController {

	@RequestMapping(value = "/employees")
	public ResponseEntity<List<Employee>> getEmployees() {
		Employee emp = new Employee("100", "Anil", 30, "M", false, Arrays.asList("Technical", "Functional"),
				"Technical Lead", "IT", "Bengaluru", "India");
		List<Employee> employees = new ArrayList<>();
		employees.add(emp);
		return ResponseEntity.ok().body(employees);
	}
}
