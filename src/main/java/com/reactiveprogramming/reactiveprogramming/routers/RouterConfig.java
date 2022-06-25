package com.reactiveprogramming.reactiveprogramming.routers;

import com.reactiveprogramming.reactiveprogramming.handlers.CustomerHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    CustomerHandler customerHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", customerHandler::getCustomers)
                .GET("/router/reactivecustomers", customerHandler::getCustomersFromStreamWithDelayElements)
                .GET("/router/reactivecustomers/{customerId}", customerHandler::getCustomerByCustomerId)
                .POST("/routers/reactivecustomers/save", customerHandler::saveCustomer)
                .build();
    }
}
