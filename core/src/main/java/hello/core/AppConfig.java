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


// 스프링 설정 정보 (스프링 빈에 이미 등록되어 있으면 그거 갖고 쓰고, 없으면 새로 만들도록 함)
@Configuration // 없으면 싱글톤 깨지는 현상이 발생 (memberRepository가 3번 호출됨)
public class AppConfig {

    // @Bean memberService -> new MemoryMemberRepository()
    // @Bean orderService -> new MemoryMemberRepository()

    // 2번 호출해서 2개의 객체가 생기면 싱글톤이 깨질까? -> 테스트로 확인

    // service
    // repository
    // repository
    // order
    // repository => 최종적으로 memberRepository가 3번 호출됨

    @Bean
    // 역할 정의
    public MemberService memberService() { // 회원 서비스 역할
        System.out.println(" call AppConfig.memberService");
        return new MemberServiceImpl(memberRepository()); // 생성자 주입
    }

    @Bean
    public static MemberRepository memberRepository() { // 회원 저장소 역할 (인터페이스 반환)

        System.out.println("AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() { // 주문 서비스 역할
        // 구현 클래스는 실행에만 집중
        System.out.println("AppConfig.orderService");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public DiscountPolicy discountPolicy() { // 할인 정책 역할
        return new RateDiscountPolicy();
    }

}
