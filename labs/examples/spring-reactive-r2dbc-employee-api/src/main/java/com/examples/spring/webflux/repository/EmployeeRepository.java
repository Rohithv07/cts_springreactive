package com.examples.spring.webflux.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.examples.spring.webflux.model.Employee;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer>{

}
