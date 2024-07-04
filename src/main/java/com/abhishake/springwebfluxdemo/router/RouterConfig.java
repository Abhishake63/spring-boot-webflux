package com.abhishake.springwebfluxdemo.router;

import com.abhishake.springwebfluxdemo.handler.CustomerHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    private final CustomerHandler customerHandler;

    public RouterConfig(CustomerHandler customerHandler) {
        this.customerHandler = customerHandler;
    }

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::getAllCustomers)
                .GET("/router/customers/{customerId}", customerHandler::getCustomer)
                .build();
    }
}
