package com.demo.OrderApp.service;

import com.demo.OrderApp.domain.CreateOrder;
import com.demo.OrderApp.domain.StoreProduct;
import com.demo.OrderApp.repository.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class )
class OrderServiceTest {
    @Mock
    OrderRepository orderRepository;

    @Mock
    StoreService storeService;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("구매수량이 재고수량보다 적을 때, 정상 주문 가능")
    public void stockQuantityTest_success() {
        // given
        int buyQuantity = 5;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map  = new HashMap<>();
        // 1번 상품에 대해서 buyQuantity만큼 구매
        map.put(1, buyQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        // mock 처리
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock);

        // when
        orderService.newOrder(createOrder);

        // then
        // 확인하려고 하는 것 : stockQuantity가 구매하는 개수만큼 차감되었는지?
        assertThat(stock.getStockQuantity()).isEqualTo(stockQuantity - buyQuantity);
    }


    @Test
    @DisplayName("구매수량이 재고수량보다 많을 때, 정상 주문 불가능")
    public void stockQuantityTest_failure() {
        // 예외 테스트 (RuntimeException)
        // given
        int buyQuantity = 100 ;
        int stockQuantity = 50;
        HashMap<Integer, Integer> map  = new HashMap<>();
        // 1번 상품에 대해서 buyQuantity만큼 구매
        map.put(1, buyQuantity);
        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();
        // mock 처리
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock);

        // when

        // then
        // 확인하려고 하는 것 : stockQuantity가 구매하는 개수만큼 차감되었는지?

        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            orderService.newOrder(createOrder);
        });

        assertThat(runtimeException.getMessage()).isEqualTo("재고가 없습니다.");
    }
}

