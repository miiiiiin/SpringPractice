package com.demo.OrderApp.controller;

import com.demo.OrderApp.domain.CreateCustomer;
import com.demo.OrderApp.domain.Customer;
import com.demo.OrderApp.domain.CustomerDTO;
import com.demo.OrderApp.service.CustomerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/api/v1/customers")
    public CustomerDTO createNewCustomer(
            @RequestParam String name,
            @RequestParam String address,
            @RequestParam String phoneNumber) {
        return customerService.newCustomer(
                CreateCustomer.builder()
                        .name(name)
                        .address(address)
                        .phoneNumber(phoneNumber)
                        .build()
        );
    }

}
