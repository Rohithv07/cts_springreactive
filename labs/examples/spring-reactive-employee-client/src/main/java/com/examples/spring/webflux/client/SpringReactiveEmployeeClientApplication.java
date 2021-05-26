package com.examples.spring.webflux.client;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootApplication
public class SpringReactiveEmployeeClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveEmployeeClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// Employee Service WebClient
		
		// STEP 1: Create instance of WebClient
		WebClient webclient = WebClient.create("http://localhost:8080");
		
		// STEP 2: Get All Employees
		
		Flux<Employee> employees = 
				  webclient.get()		// GET request
				 .uri("/employees")		// URI				 
				 .retrieve()
				 .bodyToFlux(Employee.class);
				 
		employees.subscribe(e -> System.out.printf("[GET ALL EMLOYEES] Emp ID: %s, Name: %s\n", e.getId(), e.getName()));
		
		
		// Employees Subscribtion with Backpressure
		employees
			.log()
			.subscribe(new Subscriber<Employee>() {
		    private Subscription s;
		    int onNextAmount;

			@Override
			public void onSubscribe(Subscription s) {
				System.out.println("onSubscribtion method called..");
				this.s = s;
				// making bounded subscribtion 
				s.request(2);
			}

			@Override
			public void onNext(Employee e) {
				System.out.println("onNext method called..");
				System.out.printf("[GET ALL EMLOYEES WITH BACKPRESSURE] Emp ID: %s, Name: %s\n", e.getId(), e.getName());

		        onNextAmount++;
		        if (onNextAmount % 2 == 0) {
		            s.request(2);
		        }				
			}

			@Override
			public void onError(Throwable t) {
				System.out.println("onError method called..");
				
			}

			@Override
			public void onComplete() {
				System.out.println("onComplete method called..");
				
			}
		});
		
		// STEP 3: GET Employee
		Mono<Employee> employee = 
				  webclient.get()				// GET request
				 .uri("/employees/{id}",1)		// URI				 
				 .retrieve()
				 .bodyToMono(Employee.class);		
		
		employee.subscribe(e -> System.out.printf("[GET EMLOYEE] Emp ID: %s, Name: %s\n", e.getId(), e.getName()));
	}

}
