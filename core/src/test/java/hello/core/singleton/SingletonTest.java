package hello.core.singleton;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SingletonTest {

    @Test
    @DisplayName("스프링 없는 순수 DI 컨테이너") // 요청 시 마다 객체 새로 생성 -> 메모리 낭비가 심함 (해결방안 싱글톤!)
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        // 1. 조회 (호출마다 객체 생성)
        MemberService memberService1 = appConfig.memberService();

        // 2. 조회 (호출마다 객체 생성)
        MemberService memberService2 = appConfig.memberService();

        // 참조값이 다른 것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService2 = " + memberService2);

        // memberService1 =/= memberService2
        assertThat(memberService1).isNotSameAs(memberService2);
    }

    @Test
    @DisplayName("싱긑톤 패턴 적용한 객체 사용") // 같은 객체 인스턴스 반환
    void singletonServiceTest() {
        // 자바 뜰 때 SingletonService 객체 미리 생성해둔 걸 가져다 쓰는 방식
        SingletonService instance1 = SingletonService.getInstance();
        SingletonService instance2 = SingletonService.getInstance();

        // 참조값 같은지 확인
        System.out.println("instance1 = " + instance1);
        System.out.println("instance2 = " + instance2);

        assertThat(instance1).isSameAs(instance2);
    }
}
