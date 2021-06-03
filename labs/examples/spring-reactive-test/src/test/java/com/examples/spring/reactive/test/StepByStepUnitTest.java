package com.examples.spring.reactive.test;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class StepByStepUnitTest {

    Flux<String> source = Flux.just("John", "Monica", "Mark", "Cloe", "Frank", "Casper", "Olivia", "Emily", "Cate")
      .filter(name -> name.length() == 4)
      .map(String::toUpperCase);
    
    Mono<String> mono = Mono.just("John");

    @Test
    public void shouldReturnForLettersUpperCaseStrings() {
        StepVerifier
          .create(source)
          .expectNext("JOHN")
          .expectNextMatches(name -> name.startsWith("MA"))
          .expectNext("CLOE", "CATE")
          .expectComplete()
          .verify();
    }

    @Test
    public void shouldThrowExceptionAfterFourElements() {
        Flux<String> error = source.concatWith(
          Mono.error(new IllegalArgumentException("Our message"))
        );

        StepVerifier
          .create(error)
          .expectNextCount(4)
//          .expectError()
          .expectErrorMatches(throwable -> throwable instanceof IllegalArgumentException &&
                  throwable.getMessage().equals("Our message")
          )
		.verify();
    }
    
    @Test
    public void testMono() {
    	StepVerifier
    		.create(mono)
    		.expectNext("John")
    		.expectComplete()
    		.verify();
    }

}
