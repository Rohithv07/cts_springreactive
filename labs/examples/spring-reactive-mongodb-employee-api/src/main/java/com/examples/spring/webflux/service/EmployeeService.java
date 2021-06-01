package com.examples.spring.webflux.service;

import com.examples.spring.webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface EmployeeService {
	public Flux<Employee> getAllEmployees();
	public Mono<Employee> getEmployee(String empId);
	public Mono<Employee> createEmployee(Employee employee);
	public Mono<Boolean> updateEmployee(Employee employee);
	public Mono<Boolean> deleteEmployee(String empId);
}
