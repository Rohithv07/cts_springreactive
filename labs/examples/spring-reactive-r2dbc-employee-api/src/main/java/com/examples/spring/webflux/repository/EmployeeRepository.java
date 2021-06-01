package com.examples.spring.webflux.repository;

import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

import com.examples.spring.webflux.model.Employee;

import reactor.core.publisher.Flux;

public interface EmployeeRepository extends ReactiveCrudRepository<Employee, Integer>{
	
	// find by Department
	public Flux<Employee> findByDepartment(String department);
	
	// find by Department and Country
	public Flux<Employee> findByDepartmentAndCountry(String department, String country);
	
	@Query("SELECT e FROM EMPLOYEE e WHERE empName= :empName")
	public Flux<Employee> findByEmployeeName(@Param("empName") String empName);
}
