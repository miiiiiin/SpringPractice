package com.demo.OrderApp.controller;

import com.demo.OrderApp.domain.CreateOrder;
import com.demo.OrderApp.service.OrderService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/api/v1/orders")
    public Response<Void> newOrder(
            @RequestBody NewOrderRequest request
    ) {
        // 아래 내용들 request에 포함되어 있어서 필요없음
//        HashMap<Integer, Integer> orderMap = new HashMap<>();
//        // 1번 상품에 대해서 5개 주문
//        orderMap.put(1, 5);
//        orderMap.put(2, 10);
        orderService.newOrder(CreateOrder.builder()
                        .customerId(request.getCustomerId())
                        .storeId(request.getStoreId())
                        .quantityByProduct(request.getProducts())
                .build());
        return Response.success(null);
    }
}
