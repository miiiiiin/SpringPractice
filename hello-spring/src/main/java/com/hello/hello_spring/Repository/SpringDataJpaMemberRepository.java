package com.hello.hello_spring.Repository;

import com.hello.hello_spring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// 인터페이스가 인터페이스를 받을 때에는 extends
// spring data jpa가 jpa repository를 받고 있으면 SpringDataJpaMemberRepository가 구현체를 자동으로 만들어줌. (스프링 빈에 자동 등록)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    // JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);
}
