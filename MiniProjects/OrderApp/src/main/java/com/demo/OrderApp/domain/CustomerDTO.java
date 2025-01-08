package com.demo.OrderApp.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CustomerDTO {
    // customerid까지 외부 api에 응답해주기 때문에
    // Customer 객체를 바로 반환하기보다 별도의 dto를 거친 객체를 반환하는 것이 좋음.

    private final String name;
    private final String address;
    private final String phoneNumber;
}
