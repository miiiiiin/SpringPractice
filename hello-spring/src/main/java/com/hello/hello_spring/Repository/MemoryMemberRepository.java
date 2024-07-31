package com.hello.hello_spring.Repository;

import com.hello.hello_spring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    // map으로 메모리에 저장
    private static Map<Long, Member> store = new HashMap<>();
    // 키 값 생성(실무x)
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        // store에서 꺼내어 리턴하면
        // 결과가 없으면 Optional.ofNULLABLE 로 감싸서 반환해주면 클라이언트 단에서 처리 가능
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        // 루프 돌려 필터링 (멤버 네임이 파라미터에서 넘어온 값과 같은지 확인) => 하나라도 찾아지면 반환
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
