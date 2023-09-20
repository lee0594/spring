package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    //order service: memberrepository에서 회원 찾아야 하고 discount policy 필요함
    //private final MemberRepository memberRepository = new MemoryMemberRepository(); //final이므로 값이 무조건 할당 되어야 함
    //private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    //private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    /*
    @Autowired private  MemberRepository memberRepository;
    @Autowired private  DiscountPolicy discountPolicy;
    */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;
    //final base면 기본으로 할당을 하거나(초기화) 또는 생성자 통해서 할당이 되어야 함

    /*
    @Autowired //수정자 주입(setter 주입)
    public void setMemberRepository(MemberRepository memberRepository) {
        //System.out.println("memberRepository = " + memberRepository);
        this.memberRepository = memberRepository;
    }

    @Autowired //수정자 주입(setter 주입)
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        //System.out.println("discountPolicy = " + discountPolicy);
        this.discountPolicy = discountPolicy;
    }
    */


    //@Autowired
    //private DiscountPolicy rateDiscountPolicy
    //@Autowired //생성자가 하나이므로 이걸 생략해도 자동으로 주입됨
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
    //public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        //System.out.println("memberRepository = " + memberRepository);
        //System.out.println("discountPolicy = " + discountPolicy);
        //System.out.println("1. OrderServiceImpl.OrderServiceImpl");
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
        //this.discountPolicy = rateDiscountPolicy;
    }


    /*
    @Autowired //일반 메서드 주입
    public void init(MemberRepository memberRepository, DiscountPolicy
            discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
     */

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId); //member 찾음
        int discountPrice = discountPolicy.discount(member, itemPrice); //등급(Grade)만 넘겨도 되지만 일의 확장성? 등을 고려해서 member를 통으로 넘김
        //member를 찾아서 discountpolicy에다 넘김: 최종할인된 가격을 받음
        //단일 객체 원칙 잘 지킴: Orderservide가 할인에 대해 잘 모름: 할인에 대한 변경이 필요하면 할인쪽만 잘 고치면 됨(할인 결과만 여기에 줌)
        //이제 최종 생성된 order 객체(주문)를 만들어서 반환해 주면 됨
        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
