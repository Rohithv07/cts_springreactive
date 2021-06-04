package com.examples.spring.webflux.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.examples.spring.webflux.model.Employee;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	private Map<Integer, Employee> employees = new HashMap<>();

//	{
//		// Integer id, String name, Integer age, String gender, boolean contractor,
//		// String designation, String department, String address, String country
//
//		employees.put(1, new Employee(1, "Anand", 30, "Male", false, "Lead", "IT", "Mumbai", "India"));
//		employees.put(2, new Employee(2, "Neha", 30, "Female", false, "Analyst", "Research", "Mumbai", "India"));
//		employees.put(3, new Employee(3, "Zahoor", 40, "Male", false, "Consultant", "Finance", "Mumbai", "India"));
//	}

	@Override
	public Flux<Employee> getAllEmployees() {
		return Flux.fromIterable(employees.values());
	}

	@Override
	public Mono<Employee> getEmployee(Integer empId) {
		return Mono.just(employees.get(empId));
	}

	@Override
	public Mono<Employee> createEmployee(Employee employee) {
//		employee.setId(employees.size() + 1);
		employees.put(employee.getId(), employee);
		return Mono.just(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		employees.put(employee.getId(), employee);
	}

	@Override
	public void deleteEmployee(Integer empId) {
		employees.remove(empId);
	}
	
	
	public void deleteAll() {
		employees.clear();
	}

}
