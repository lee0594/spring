package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;

public class AppConfig {

    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }
    //어디서 appconfig를 통해 memberservice를 불러다 쓰면 memberservice의 구현체인 객체가 생성이 되는데 memorymemberrepository가 여기서 들어감

    private MemoryMemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    public OrderService orderService() {
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    //orderserviceimpl이 두 개의 객체를 참조함

    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
//ctrl+e: 과거 히스토리를 다 볼 수 있는 단축키
//ctrl+alt+m: extract method 사용 가능, return type은 interface로 고르기
//이제 method 명을 보면 역할이 다 드러남, return type을 보면 구현 클래스가 보임