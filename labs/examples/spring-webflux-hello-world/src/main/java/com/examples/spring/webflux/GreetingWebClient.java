package com.examples.spring.webflux;

import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

public class GreetingWebClient {
	public static void main(String[] args) {
		WebClient client = WebClient.create("http://localhost:8080");
		System.out.println(client.get());
		
		Mono<Greeting> greetingMono = client.get()
				  .uri("/greetings")
				  .retrieve()
				  .bodyToMono(Greeting.class);

		greetingMono.subscribe(System.out::println);
		
		greetingMono.subscribe(e -> System.out.println("########### " + e.getMessage()));
		
		greetingMono.block();
	}
}
