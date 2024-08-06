package com.hello.hello_spring.service;

import com.hello.hello_spring.Repository.MemberRepository;
import com.hello.hello_spring.Repository.MemoryMemberRepository;
import com.hello.hello_spring.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// 순수한 자바 클래스이기 때문에 어노테이션으로 표시. (@Service를 보고 스프링이 스프링 컨테이너에 MemberService를 등록해줌)
@Service
@Transactional // 데이터를 저장하고 변경할 때 항상 트랜잭션이 있어야 함. (jpa는 join 시 모든 데이터 변경이 다 트랜잭션 안에서 실행되어야 함)
public class MemberService {

    private final MemberRepository memberRepository;

    // 멤버 리포지토리를 직접 생성하는게 아니라 외부에서 파라미터로 넣어주도록 설정

    // MemberService는 MemberRepository가 필요
    // MemberService를 스프링이 생성할 때, 스프링 컨테이너에 등록하면서 생성자 호출
    // 그 때 Autowired가 있으면 MemberRepository가 필요하다는 것을 캐치해서 스프링 컨테이너에 있는 MemberRepository를 넣어줌.
    // MemoryMemberRepository를 서비스에 주입해줌
    @Autowired
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
