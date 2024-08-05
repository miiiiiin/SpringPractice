package com.hello.hello_spring;

import com.hello.hello_spring.Repository.MemberRepository;
import com.hello.hello_spring.Repository.MemoryMemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository()); // MemberService를 스프링 빈에 등록
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
