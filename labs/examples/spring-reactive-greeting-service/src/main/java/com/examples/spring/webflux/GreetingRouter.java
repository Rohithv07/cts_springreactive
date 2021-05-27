package com.examples.spring.webflux;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class GreetingRouter {

  @Bean
  public RouterFunction<ServerResponse> greetingsRoute(GreetingHandler greetingHandler) {

    return RouterFunctions
    		.route(RequestPredicates.GET("/greetings"), greetingHandler::greetings);
  }
  
  @Bean
  public RouterFunction<ServerResponse> sayHelloRoute(GreetingHandler greetingHandler) {

    return RouterFunctions
    		.route(RequestPredicates.GET("/sayHello"), greetingHandler::sayHello);
  }
  
  @Bean
  public RouterFunction<ServerResponse> greetingsWithHandlerLogicRoute(GreetingHandler greetingHandler) {

    return RouterFunctions
    		.route(RequestPredicates.GET("/greetings1"), req -> ServerResponse.ok().body(BodyInserters.fromValue("Hello, Spring Reactive With BuiltIn Handler Logic!")));
  } 

  
}