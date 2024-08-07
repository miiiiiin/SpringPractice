package com.hello.hello_spring;

import aop.TimeTraceAop;
import com.hello.hello_spring.Repository.JpaMemberRepository;
import com.hello.hello_spring.Repository.MemberRepository;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    // SPRING DATA JPA
    private final MemberRepository memberRepository;

    // 그냥 injection 받으면 스프링 데이터 jpa가 구현체를 만들어놓은게 등록됨
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // aop 스프링 빈에 등록 or ComponentScan
//    @Bean
//    public TimeTraceAop timeTraceAop() {
//        return new TimeTraceAop();
//    }


    // JPA
//    @PersistenceContext (생성자 외에 이렇게 해도 됨)
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

//    private DataSource dataSource;
//
//
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Bean
    public MemberService memberService() {

//        return new MemberService(memberRepository()); // MemberService를 스프링 빈에 등록

        // MemberService 의존 관계 세팅
        return new MemberService(memberRepository);
    }

    // spring data jpa 할 때에는 필요없음.
//    @Bean
//    public MemberRepository memberRepository() {

//        return new MemoryMemberRepository();
//        return new JdbcMemberRepository(dataSource);
//        return new JdbcTempleteMemberRepository(dataSource);
//        return new JpaMemberRepository(em);
//    }
}
