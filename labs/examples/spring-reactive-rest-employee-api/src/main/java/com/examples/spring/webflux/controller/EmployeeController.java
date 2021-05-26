package com.examples.spring.webflux.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examples.spring.webflux.model.Employee;
import com.examples.spring.webflux.model.ResponseMessage;
import com.examples.spring.webflux.service.EmployeeService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

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

	@GetMapping("/{id}")
	public Mono<Employee> getEmployee(@PathVariable Integer id) {
		return empService.getEmployee(id);
	}

	@PostMapping
	public Mono<ResponseEntity<ResponseMessage>> createEmployee(@RequestBody Employee employee)
			throws URISyntaxException {
		Mono<Employee> emp = empService.createEmployee(employee);

		StringBuilder locationStr = new StringBuilder();
		emp.subscribe(e -> locationStr.append("http://localhost:8080/employees/").append(e.getId()));

		// Getting current resource path
		URI location = new URI(locationStr.toString());

		return Mono.just(ResponseEntity.created(location).body(this.getResponse(employee.getId(), "Employee Created")));
	}

	@PutMapping("/{id}")
	public Mono<ResponseEntity<ResponseMessage>> updateEmployee(@RequestBody Employee employee,
			@PathVariable Integer id) {
		employee.setId(id);
		empService.updateEmployee(employee);

		return Mono.just(ResponseEntity.ok().body(this.getResponse(employee.getId(), "Employee Updated")));
	}

	@DeleteMapping("/{id}")
	public Mono<ResponseEntity<ResponseMessage>> deleteEmployee(@PathVariable Integer id) {
		empService.deleteEmployee(id);
		ResponseMessage response = getResponse(id, "Employee Deleted");

		return Mono.just(ResponseEntity.accepted().body(response));
	}

	private ResponseMessage getResponse(Integer id, String message) {
		ResponseMessage response = new ResponseMessage();
		response.setId(id);
		response.setStatus(HttpStatus.OK.name());
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage(message);
		return response;
	}

}
