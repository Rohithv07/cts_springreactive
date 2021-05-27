package com.examples.spring.webflux;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringReactiveGreetingService {

	public static void main(String[] args) {
		SpringApplication.run(SpringReactiveGreetingService.class, args);
		
	    GreetingWebClient gwc = new GreetingWebClient();
	    System.out.println(gwc.getResult());		
	}

}
