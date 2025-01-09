package com.demo.OrderApp.repository;

import com.demo.OrderApp.domain.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Integer> {

}
