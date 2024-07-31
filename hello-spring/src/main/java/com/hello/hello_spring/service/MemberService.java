package com.hello.hello_spring.service;

import com.hello.hello_spring.Repository.MemberRepository;
import com.hello.hello_spring.Repository.MemoryMemberRepository;
import com.hello.hello_spring.domain.Member;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 리포지토리를 직접 생성하는게 아니라 외부에서 파라미터로 넣어주도록 설정

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * 회원가입
     * @param member
     * @return
     */
    public Long join(Member member) {
        // 같은 이름의 중복 회원은 안됨! (중복 회원 검증)
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        // ifPresent() -> 값이 있으면 member가 들어옴.
        // optional 이기 때문에 가능
        memberRepository.findByName(member.getName())
            .ifPresent(m -> {
                throw new IllegalStateException("이미 존재하는 회원입니다.");
            });
    }

    /**
     * 전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
