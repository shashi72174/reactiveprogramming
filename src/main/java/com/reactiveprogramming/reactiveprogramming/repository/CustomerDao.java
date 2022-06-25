package com.reactiveprogramming.reactiveprogramming.repository;

import com.reactiveprogramming.reactiveprogramming.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

@Component
public class CustomerDao {

    public List<Customer> getCustomers() {
        return LongStream.rangeClosed(1,50).mapToObj(value -> {
            try {
                Thread.sleep(1000);
            }catch (Exception e) {
                e.printStackTrace();
            }
            return new Customer(value, "Customer " + value);
        }).collect(Collectors.toList());
    }


    public Flux<Customer> getCustomersFromFlux() {
        return Flux.range(1,50).map(integer -> new Customer((long)integer, "Customer "+integer)).delayElements(Duration.ofSeconds(1));
    }

    public Flux<Customer> getCustomersFromFluxRouter() {
        return Flux.range(1,50).map(integer -> new Customer((long)integer, "Customer "+integer));
    }

    public Flux<Customer> getCustomersFromFluxRouterWithDelayElements() {
        return Flux.range(1,50).map(integer -> new Customer((long)integer, "Customer "+integer)).delayElements(Duration.ofSeconds(1));
    }

    public Mono<Customer> getCustomerByCustomerId(Long id) {
        return Flux.range(1,50).map(integer -> new Customer((long)integer, "Customer "+integer)).elementAt(id.intValue());
    }
}
