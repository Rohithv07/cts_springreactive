package com.examples.spring.webflux.service;

import com.examples.spring.webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	public Flux<Employee> getAllEmployees();
	public Mono<Employee> getEmployee(Integer empId);
	public Mono<Employee> createEmployee(Employee employee);
	public void updateEmployee(Employee employee);
	public void deleteEmployee(Integer empId);
}
