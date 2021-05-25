package com.examples.spring.boot.rest.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examples.spring.boot.rest.model.Employee;
import com.examples.spring.boot.rest.repository.EmployeeRepository;

@Service
public class EmployeeService {

//	private static Map<Integer, Employee> employees = new LinkedHashMap<Integer, Employee>();
	
	@Autowired
	EmployeeRepository empRepo;
	
	public Integer add(Employee employee) {
	
//		employee.setId(employees.size() + 1);
//
//		employees.put(employee.getId(), employee);
		empRepo.save(employee);
		
		return employee.getId();
	}

	public void update(Employee employee) {
		empRepo.save(employee);
//		employees.put(employee.getId(), employee);
	}

	public Employee get(Integer empId) {
		Optional<Employee> emp = empRepo.findById(empId); 
		return  emp.isPresent() ? emp.get() : null;
		
//		return employees.get(empId);
	}

	public void delete(Integer empId) {
		
		empRepo.deleteById(empId);
		
//		employees.remove(empId);
	}

	public List<Employee> list() {
		return empRepo.findAll();
		
//		return new ArrayList<Employee>(employees.values());
	}
	
	public void clear()
	{
		empRepo.deleteAll();
//		employees.clear();
	}

}
