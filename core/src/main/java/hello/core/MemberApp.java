package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {
//        MemberService memberService = new MemberServiceImpl(); // AppConfig 통해서 생성하도록 수정
//        AppConfig appConfig = new AppConfig();
//        MemberService memberService = appConfig.memberService();

        // ApplicationContext : 스프링 컨테이너, BEAN 들을 다 관
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        // AnnotationConfigApplicationContext(AppConfig.class): Appconfig에 있는 환경 설정 정보를 가지고 스프링에 등록된 Bean들을 스프링 컨테이너에 다 넣어서 관리해줌
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        // Appconfig 메서드 이름 객체 찾아옴 (이름, 반환타입)

        // Long 타입이라서 숫자뒤에 L 붙여줌
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
