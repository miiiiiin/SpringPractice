package com.demo.OrderApp.service;

import com.demo.OrderApp.domain.CreateOrder;
import com.demo.OrderApp.domain.Order;
import com.demo.OrderApp.domain.StoreProduct;
import com.demo.OrderApp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StoreService storeService;

    public OrderService(OrderRepository orderRepository, StoreService storeService) {
        this.orderRepository = orderRepository;
        this.storeService = storeService;
    }

    public void newOrder(CreateOrder createOrder) {
        List<StoreProduct> storeProducts = new ArrayList<>();
        // 구매하고자 하는 갯수가 상품 재고보다 많으면 예외 처리
        for (Map.Entry<Integer, Integer> entry : createOrder.getQuantityByProduct().entrySet()) {
            Integer productId = entry.getKey();
            Integer buyQuantity = entry.getValue();

            StoreProduct storeProduct = storeService.getStoreProduct(
                    createOrder.getStoreId(),
                    productId
            );

            int stockQuantity = storeProduct.getStockQuantity();

            if (buyQuantity > stockQuantity) {
                throw new RuntimeException("재고가 없습니다.");
            }
            // 상품 재고에서 구매 수량 차감 후 업데이트
            storeProduct.adjustStockQuantity(buyQuantity);
            // 저장
            storeProducts.add(storeProduct);
        }
        Order entity = Order.newOrder(createOrder);
        orderRepository.save(entity);
        // 재고 데이터 갱신 저장
        storeService.saveAll(storeProducts);
    }
}
