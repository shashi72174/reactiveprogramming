package com.reactiveprogramming.reactiveprogramming.service;

import com.reactiveprogramming.reactiveprogramming.dto.Customer;
import com.reactiveprogramming.reactiveprogramming.repository.CustomerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao customerDao;

    public List<Customer> getAllCustomers() {
        return customerDao.getCustomers();
    }

    public Flux<Customer> getAllCustomersFromFlux() {
        return customerDao.getCustomersFromFlux();
    }
}
