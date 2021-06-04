package com.examples.spring.webflux;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class HelloWorldController {

		// http://localhost:8080/greetings
		@RequestMapping("/greetings")
		public String sayHello() {
			return "Hello!!! Welcome to Spring Reactive Training :)";
		}
		
		// Mono or Flux types
		
		@RequestMapping("/greetingsMono")
		public Mono<String> greetingsMono() {
			return Mono.just("Hello!!! Welcome to Spring Reactive Training :)");
		}
		
		@RequestMapping("/greetingsFlux")
		public Flux<String> greetingsFlux() {
			return Flux.just("Hello!!! ", "Welcome to Spring Reactive Training :)");
		}		
}
