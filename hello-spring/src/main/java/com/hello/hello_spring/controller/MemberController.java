package com.hello.hello_spring.controller;

import com.hello.hello_spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class MemberController {
    private final MemberService memberService;

    // 스프링 컨테이너에 생성자 만들어 등록
    // AUTOWIRED : 스프링 컨테이너가 뜰 때 생성자 호출. autowired는 MemberService를 스프링이
    // 스프링 컨테이너에 있는 MemberService를 가져다가 연결을 시켜줌.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}

