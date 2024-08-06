package com.hello.hello_spring.Repository;

import com.hello.hello_spring.domain.Member;
import jakarta.persistence.EntityManager;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// JPA 쓰려면 항상 트랜잭션에 주의해야함 그래서 서비스 계층에(MemberService) @Transaction
public class JpaMemberRepository implements MemberRepository {

    // jpa는 EntityManager로 모든게 동작함

    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        // jpa가 insert query 만들어서 db에 집어넣고, member에다 setId까지 해줌
        em.persist(member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);// 조회할 타입과 식별자 pk값 넣어주면 em에서 조회해줌
        return Optional.ofNullable(member); // 값이 없을 수도 있기 대문에 optional로 반환
    }


    // primary key 기반이 아닌 다른 것들은 jpaql을 작성해서 반환
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        // 하나만 찾음
        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // jpaql은 테이블 대상이 아닌 객체를 대상으로 쿼리를 날림 (정확히는 entity를 대상으로 쿼리 날림)
        // alias m으로 설정 (객체 자체를 select해서 조회함) => as m (as 생략)
        return em.createQuery("select m from Member m", Member.class)
                .getResultList();


    }
}
