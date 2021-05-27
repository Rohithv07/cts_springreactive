package com.examples.spring.webflux;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@Component
public class GreetingHandler {

  public Mono<ServerResponse> greetings(ServerRequest request) {
    return ServerResponse.ok()
    		.contentType(MediaType.TEXT_PLAIN)
    		.body(BodyInserters.fromValue("Hello, Spring Reactive!"));
  	}
  
  public Mono<ServerResponse> sayHello(ServerRequest request) {
	    return ServerResponse.ok()
	    		.body(BodyInserters.fromValue("Hello!!!!!"));
	  	}  
}
