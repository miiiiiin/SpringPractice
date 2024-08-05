package com.hello.hello_spring.controller;

import com.hello.hello_spring.domain.Member;
import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


// 컨트롤러 - 서비스 - 리포지토리 (패턴 정형화)
// 컨트롤러 통해 외부 요청 받고 그 다음 서비스에서 비즈니스 로직을 만듬, 이후 리포지토리에서 데이터를 저장
@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링 컨테이너에 생성자 만들어 등록
    // AUTOWIRED : 스프링 컨테이너가 뜰 때 생성자 호출. autowired는 MemberService를 스프링이
    // 스프링 컨테이너에 있는 MemberService를 가져다가 연결을 시켜줌.
    // MemberController가 생성이 될 때 스프링 빈애 등록되어있는 MemberService 객체를 가져다 넣어줌 (DI:의존성 주입)
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());
        memberService.join(member);
        return "redirect:/"; // 홈화면으로 리다이렉트
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // members list 전체를 모델에 담음
        return "members/memberList";
    }

}

