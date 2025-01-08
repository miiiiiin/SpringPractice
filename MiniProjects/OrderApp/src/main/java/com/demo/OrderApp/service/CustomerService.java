package com.demo.OrderApp.service;

import com.demo.OrderApp.domain.CreateCustomer;
import com.demo.OrderApp.domain.Customer;
import com.demo.OrderApp.domain.CustomerDTO;
import com.demo.OrderApp.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomerService {
    // 생성자 기반 의존성 주입
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Transactional
    public CustomerDTO newCustomer(CreateCustomer customer) {
        // 데이터에 저장할 수 있는 entity 반환
        Customer entity = Customer.newCustomer(customer);
        // 외부에서 createCustomer 객체를 만들어서 넣어주면 하나의 customer 저장할 수 있음
        Customer saved = customerRepository.save(entity);
        return CustomerDTO.builder()
                .name(saved.getName())
                .address(saved.getAddress())
                .phoneNumber(saved.getPhoneNumber())
                .build();
    }
}
