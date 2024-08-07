package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // but, MemberRepository와 MemoryMemberRepository 둘 다를 의존하고 있음.
    private  final MemberRepository memberRepository = new MemoryMemberRepository();
    @Override
    public void join(Member member) {
        memberRepository.save(member); // MemoryMemberRepository에 있는 save가 호출됨.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
