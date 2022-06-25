package com.reactiveprogramming.reactiveprogramming;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@SpringBootTest
public class MonoFluxTest {

    @Test
    public void getMono() {
        try {
            Mono<Object> mono = Mono.just("Hey Reactive Programming").then(Mono.error(new RuntimeException("something went wrong"))).log();
            mono.subscribe(System.out::println, ex -> System.out.println(ex.getMessage()));
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getFlux() {
        Flux<String> stringFlux = Flux.just("spring", "spring reactive", "spring microservices").concatWith(Flux.just("Junit"))
                .concatWith(Flux.error(new RuntimeException("something went wrong")))
                .concatWithValues("this is after error occurred")
                .log();
        stringFlux.subscribe(System.out::println);
    }
}
