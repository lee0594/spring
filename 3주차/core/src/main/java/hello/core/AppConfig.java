package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //(application의)설정(구성)정보
//annotation 기반으로 config를 하고 있음
public class AppConfig {

    //@Bean memberService -> new MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()

    //예상
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository
    //이때 method 호출 순서는 보장하지 않음, 예시

    //실제 ConfigurationSingletonTest에서 돌려봤을때
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //:memberRepository method가 한번만 호출된 것을 알 수 있음
    //spring이 어떠한 경우에도 싱글톤을 보장해준다는 것을 알 수 있음

    @Bean
    public MemberService memberService() {
        System.out.println("call AppConfig.memberService");
        //soutm 사용
        return new MemberServiceImpl(memberRepository());
    }
    //어디서 appconfig를 통해 memberservice를 불러다 쓰면 memberservice의 구현체인 객체가 생성이 되는데 memorymemberrepository가 여기서 들어감

    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    public OrderService orderService() {
        System.out.println("call AppConfig.orderService");
        return new OrderServiceImpl(
                memberRepository(),
                discountPolicy());
    }
    //orderserviceimpl이 두 개의 객체를 참조함

    @Bean
    public DiscountPolicy discountPolicy() {
        //return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
//ctrl+e: 과거 히스토리를 다 볼 수 있는 단축키
//ctrl+alt+m: extract method 사용 가능, return type은 interface로 고르기
//이제 method 명을 보면 역할이 다 드러남, return type을 보면 구현 클래스가 보임
//각 method에 @Bean이라 적어주면 orderService, discountPolicy, memberService, memberRepositoy들이 다 spring container에 등록됨
//(각각의 value는 return 값으로)