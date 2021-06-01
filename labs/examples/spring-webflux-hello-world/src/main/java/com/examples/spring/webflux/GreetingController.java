package com.examples.spring.webflux;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/greetings")
public class GreetingController {
	
	@GetMapping
	public Mono<Greeting> greetings() {
		return Mono.just(new Greeting("Welcome to Spring Webflux"));
	}
}
