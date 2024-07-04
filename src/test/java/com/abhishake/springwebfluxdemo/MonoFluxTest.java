package com.abhishake.springwebfluxdemo;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class MonoFluxTest {

    @Test
    public void testMono() {
        Mono<String> hello = Mono.just("hello").log();
        hello.subscribe(System.out::println);
    }

    @Test
    public void testMonoErrorHandling() {
        Mono<?> hello = Mono.just("hello")
                .then(Mono.error(new RuntimeException("Exception Found")))
                .log();
        hello.subscribe(System.out::println, (e) -> System.out.println("Error: " + e.getMessage()));
    }

    @Test
    public void testFlux() {
        Flux<String> justString = Flux.just("hello", "world").log();
        justString.subscribe(System.out::println);
    }

    @Test
    public void testFluxErrorHandling() {
        Flux<String> justString = Flux.just("hello", "world")
                .concatWith(Flux.error(new RuntimeException("Exception found with flux")))
                .log();
        justString.subscribe(System.out::println, (e) -> System.out.println("Error: " + e.getMessage()));
    }
}
