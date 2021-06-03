package com.examples.messaging.rabbitmq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.filter.reactive.HiddenHttpMethodFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;

import reactor.core.publisher.Mono;

@SpringBootApplication
@EnableConfigurationProperties(DestinationsConfig.class)
public class SpringWebfluxAmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringWebfluxAmqpApplication.class, args);
    }

    
}