package com.examples.spring.webflux.router;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.POST;
import static org.springframework.web.reactive.function.server.RequestPredicates.PUT;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import com.examples.spring.webflux.model.Employee;
import com.examples.spring.webflux.service.EmployeeService;


@Configuration
public class EmployeeRouter {
	
	@Autowired
	EmployeeService empService;
	
	@Bean
	RouterFunction<ServerResponse> getEmployeeByIdRoute() {
	  return route(GET("/employees/{id}"), 
			  req -> ok().body(empService.getEmployee(Integer.valueOf(req.pathVariable("id"))),Employee.class));
	}
	
	@Bean
	RouterFunction<ServerResponse> getAllEmployeesRoute() {
	  return route(GET("/employees"), 
	    req -> ok().body(empService.getAllEmployees(), Employee.class));
	}	
	
	@Bean
	RouterFunction<ServerResponse> createEmployeeRoute() {
		
	  return route(POST("/employees"), 
	    req -> req.body(BodyExtractors.toMono(Employee.class))
	      .doOnNext(empService::createEmployee)      
	      .then(ok().bodyValue("Employee Created")));
	}
	
	@Bean
	RouterFunction<ServerResponse> updateEmployeeRoute() {
		
	  return route(PUT("/employees/{id}"), 
	    req -> req.body(BodyExtractors.toMono(Employee.class))
	      .map(e -> {e.setId(Integer.valueOf(req.pathVariable("id"))); return e;})
	      .doOnNext(empService::updateEmployee)      
	      .then(ok().bodyValue("Employee Updated")));
	}
	
}
