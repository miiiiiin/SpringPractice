package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 설정 정
public class AppConfig {

    @Bean
    // 역할 정의
    public MemberService memberService() { // 회원 서비스 역할
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public static MemberRepository memberRepository() { // 회원 저장소 역할 (인터페이스 반환)
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() { // 주문 서비스 역할
        // 구현 클래스는 실행에만 집중
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() { // 할인 정책 역할
        return new RateDiscountPolicy();
    }
}
