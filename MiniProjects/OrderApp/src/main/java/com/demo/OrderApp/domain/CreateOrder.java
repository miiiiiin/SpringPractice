package com.demo.OrderApp.domain;

import lombok.Builder;
import lombok.Getter;
import java.util.Map;
@Getter
@Builder
public class CreateOrder {
    private int customerId;
    private int storeId; 
    // 상품별 주문 개수
    private Map<Integer, Integer> quantityByProduct; // ["아이스 아메리카노"(productId), 3]
}
