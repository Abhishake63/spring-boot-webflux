package com.abhishake.springwebfluxdemo.handler;

import com.abhishake.springwebfluxdemo.dao.CustomerDao;
import com.abhishake.springwebfluxdemo.dto.Customer;
import com.abhishake.springwebfluxdemo.service.CustomerService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    private final CustomerDao customerDao;

    public CustomerHandler(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    public Mono<ServerResponse> getAllCustomers(ServerRequest serverRequest) {
        Flux<Customer> customersStream = customerDao.getCustomersStream();
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM)
                .body(customersStream, Customer.class);
    }

    public Mono<ServerResponse> getCustomer(ServerRequest serverRequest) {

        int customerId = Integer.parseInt(serverRequest.pathVariable("customerId"));
        Mono<Customer> customerMono = customerDao.getCustomersStream()
                .filter(customer -> customer.getId() == customerId).next();
        return ServerResponse.ok().body(customerMono, Customer.class);
    }
}
