package com.reactiveprogramming.reactiveprogramming.controller;

import com.reactiveprogramming.reactiveprogramming.dto.Customer;
import com.reactiveprogramming.reactiveprogramming.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/v1")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        long start = System.currentTimeMillis();
        List<Customer> allCustomers = customerService.getAllCustomers();
        System.out.println(System.currentTimeMillis() - start);
        return allCustomers;
    }

    @GetMapping(value = "/reactive-customers", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Customer> getAllCustomersFromFlux() {
        long start = System.currentTimeMillis();
        Flux<Customer> allCustomers = customerService.getAllCustomersFromFlux();
        System.out.println(System.currentTimeMillis() - start);
        return allCustomers;
    }
}