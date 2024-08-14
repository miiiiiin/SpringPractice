package hello.core.member;

public class MemberServiceImpl implements MemberService {

    // but, MemberRepository와 MemoryMemberRepository 둘 다를 의존하고 있음.
    // private  final MemberRepository memberRepository = new MemoryMemberRepository();

    // 더 이상 MemoryMemberRepository를 의존하지 않게 되고 인터페이스인 MemberRepository만 의존하게 됨. (DIP 지킴)
    private final MemberRepository memberRepository; // 추상화에만 의 존.

    public  MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
        // 생성자 통해서 멤버리포지토리에 구현체 뭐 들어갈지 선택
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member); // MemoryMemberRepository에 있는 save가 호출됨.
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
