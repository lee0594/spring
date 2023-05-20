package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {
    //order service: memberrepository에서 회원 찾아야 하고 discount policy 필요함
    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //member 찾음
        int discountPrice = discountPolicy.discount(member, itemPrice); //등급(Grade)만 넘겨도 되지만 일의 확장성? 등을 고려해서 member를 통으로 넘김
        //member를 찾아서 discountpolicy에다 넘김: 최종할인된 가격을 받음
        //단일 객체 원칙 잘 지킴: Orderservide가 할인에 대해 잘 모름: 할인에 대한 변경이 필요하면 할인쪽만 잘 고치면 됨(할인 결과만 여기에 줌)
        //이제 최종 생성된 order 객체(주문)를 만들어서 반환해 주면 됨
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
