package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService {

//    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 메모리 멤버 리포지토

//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy(); // 정률 할인 정책
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); // 고정 할인 정책
    // 그냥 DiscountPolicy(인터페이스/추상) 뿐만 아니라 구체 클래스인 FixDiscountPolicy도 의존하고 있음. (DIP 위반) => 추상에만 의존해야 함.

    // 추상 인터페이스에만 의존하도록 수정
    // final이 있으면 무조건 생성자를 통해서 할당이 되어야 함.
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);
        // OrderService 입장에서는 할인에 대해 아는 정보 x. 그냥 discountPolicy에서 알아서 해주길 바람.
        // 단일 책임의 원칙을 잘 지킨 예
        return new Order(memberId, itemName, itemPrice, discountPrice); // 주문 만들어 반환
    }
}
