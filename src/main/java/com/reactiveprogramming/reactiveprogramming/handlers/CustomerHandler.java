package com.reactiveprogramming.reactiveprogramming.handlers;

import com.reactiveprogramming.reactiveprogramming.dto.Customer;
import com.reactiveprogramming.reactiveprogramming.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest serverRequest) {
        System.out.println("getCustomers from customerhandler");
        Flux<Customer> customersFromFluxRouter = customerDao.getCustomersFromFluxRouter().log();
        return ServerResponse.ok().body(customersFromFluxRouter, Customer.class);
    }

    public Mono<ServerResponse> getCustomersFromStreamWithDelayElements(ServerRequest serverRequest) {
        System.out.println("getCustomersFromStreamWithDelayElements from customerhandler");
        Flux<Customer> customersFromFluxRouter = customerDao.getCustomersFromFluxRouterWithDelayElements().log();
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(customersFromFluxRouter, Customer.class);
    }

    public Mono<ServerResponse> getCustomerByCustomerId(ServerRequest serverRequest) {
        System.out.println("getCustomersFromStreamWithDelayElements from customerhandler");
        Mono<Customer> customerMono = customerDao.getCustomerByCustomerId(Long.parseLong(serverRequest.pathVariable("customerId"))).log();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        System.out.println("customer saved successfully");
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        return ServerResponse.ok().body(customerMono, Customer.class);
    }
}
