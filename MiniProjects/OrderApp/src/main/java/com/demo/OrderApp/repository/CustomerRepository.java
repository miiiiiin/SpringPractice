package com.demo.OrderApp.repository;

import com.demo.OrderApp.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Integer> {

}
