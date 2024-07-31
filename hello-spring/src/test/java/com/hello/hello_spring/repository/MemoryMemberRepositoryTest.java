package com.hello.hello_spring.repository;

import com.hello.hello_spring.Repository.MemberRepository;
import com.hello.hello_spring.Repository.MemoryMemberRepository;
import com.hello.hello_spring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class MemoryMemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 순서대로 진행되는게 아니라서 변수값을 클리어 해주어야함.
    // callback method
    @AfterEach
    public void afterEach() {
        repository.clearStore();
        // 테스트가 실행되고 끝날때마다 repository 저장소를 지움. 그러면 테스트 메서드 실행 순서와 관계없어짐
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        // 검증 (new에서 만든 객체와 db의 객체가 동일하면)
        Member result = repository.findById(member.getId()).get();
//        System.out.println("result= " + (result == member));
//        Assertions.assertEquals(member, result);
        assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);

    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2);
    }
}
