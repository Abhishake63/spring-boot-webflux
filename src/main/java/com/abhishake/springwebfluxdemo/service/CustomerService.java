package com.abhishake.springwebfluxdemo.service;

import com.abhishake.springwebfluxdemo.dao.CustomerDao;
import com.abhishake.springwebfluxdemo.dto.Customer;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerDao customerDao;

    public CustomerService(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public List<Customer> getCustomers() {

        Long start = System.currentTimeMillis();
        List<Customer> customers = customerDao.getCustomers();
        Long end = System.currentTimeMillis();
        System.out.println("Total time taken: " + (end - start));
        return customers;
    }

    public Flux<Customer> getCustomersStream() {

        Long start = System.currentTimeMillis();
        Flux<Customer> customers = customerDao.getCustomersStream();
        Long end = System.currentTimeMillis();
        System.out.println("Total time taken: " + (end - start));
        return customers;
    }
}
