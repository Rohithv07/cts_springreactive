package com.examples.spring.webflux.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.spring.webflux.model.Employee;
import com.examples.spring.webflux.repository.EmployeeRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	EmployeeRepository empRepo;

	@Override
	public Flux<Employee> getAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Mono<Employee> getEmployee(Integer empId) {
		return empRepo.findById(empId);
	}

	@Override
	public Mono<Employee> createEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public Mono<Boolean> updateEmployee(Employee employee) {
		try {		

			empRepo.save(employee).block();
		}catch(Exception e) {
			return Mono.just(Boolean.FALSE);
		}
		return Mono.just(Boolean.TRUE);
	}

	@Override
	public Mono<Boolean> deleteEmployee(Integer empId) {
		try {
			empRepo.deleteById(empId).block();
		}catch(Exception e) {
			return Mono.just(Boolean.FALSE);
		}
		return Mono.just(Boolean.TRUE);
	}

}
